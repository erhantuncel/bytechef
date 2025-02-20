import RequiredMark from '@/components/RequiredMark';
import {Badge} from '@/components/ui/badge';
import {Button} from '@/components/ui/button';
import {Label} from '@/components/ui/label';
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from '@/components/ui/select';
import ConnectionDialog from '@/pages/platform/connection/components/ConnectionDialog';
import ConnectionParameters from '@/pages/platform/connection/components/ConnectionParameters';
import {ConnectionI, useConnectionQuery} from '@/pages/platform/connection/providers/connectionReactQueryProvider';
import useWorkflowNodeDetailsPanelStore from '@/pages/platform/workflow-editor/stores/useWorkflowNodeDetailsPanelStore';
import {WorkflowConnection, WorkflowTestConfigurationConnection} from '@/shared/middleware/platform/configuration';
import {useSaveWorkflowTestConfigurationConnectionMutation} from '@/shared/mutations/platform/workflowTestConfigurations.mutations';
import {useGetComponentDefinitionQuery} from '@/shared/queries/platform/componentDefinitions.queries';
import {useGetConnectionDefinitionQuery} from '@/shared/queries/platform/connectionDefinitions.queries';
import {WorkflowNodeDynamicPropertyKeys} from '@/shared/queries/platform/workflowNodeDynamicProperties.queries';
import {WorkflowNodeOptionKeys} from '@/shared/queries/platform/workflowNodeOptions.queries';
import {WorkflowTestConfigurationKeys} from '@/shared/queries/platform/workflowTestConfigurations.queries';
import {useQueryClient} from '@tanstack/react-query';
import {PlusIcon} from 'lucide-react';
import {useState} from 'react';
import {twMerge} from 'tailwind-merge';

const ConnectionTabConnectionSelect = ({
    workflowConnection,
    workflowConnectionsCount,
    workflowId,
    workflowNodeName,
    workflowTestConfigurationConnection,
}: {
    workflowConnection: WorkflowConnection;
    workflowConnectionsCount: number;
    workflowId: string;
    workflowNodeName: string;
    workflowTestConfigurationConnection?: WorkflowTestConfigurationConnection;
}) => {
    const [connectionId, setConnectionId] = useState<number | undefined>();
    const [currentConnection, setCurrentConnection] = useState<ConnectionI>();

    const {currentNode, setCurrentNode} = useWorkflowNodeDetailsPanelStore();

    const {ConnectionKeys, useCreateConnectionMutation, useGetConnectionTagsQuery, useGetConnectionsQuery} =
        useConnectionQuery();

    if (workflowTestConfigurationConnection) {
        if (connectionId !== workflowTestConfigurationConnection.connectionId) {
            setConnectionId(workflowTestConfigurationConnection.connectionId);
        }
    }

    const {data: componentDefinition} = useGetComponentDefinitionQuery({
        componentName: workflowConnection.componentName,
        componentVersion: workflowConnection.componentVersion,
    });

    const {data: connectionDefinition} = useGetConnectionDefinitionQuery({
        componentName: workflowConnection.componentName,
        componentVersion: workflowConnection.componentVersion,
    });

    const {data: componentConnections} = useGetConnectionsQuery!(
        {
            componentName: componentDefinition?.name,
            connectionVersion: componentDefinition?.connection?.version,
        },
        !!componentDefinition
    );

    const queryClient = useQueryClient();

    const saveWorkflowTestConfigurationConnectionMutation = useSaveWorkflowTestConfigurationConnectionMutation({
        onSuccess: () => {
            queryClient.invalidateQueries({
                queryKey: WorkflowTestConfigurationKeys.workflowTestConfigurations,
            });
        },
    });

    const handleValueChange = (connectionId: number, workflowConnectionKey: string) => {
        if (!connectionId) {
            return;
        }

        saveWorkflowTestConfigurationConnectionMutation.mutate({
            saveWorkflowTestConfigurationConnectionRequest: {
                connectionId,
            },
            workflowConnectionKey,
            workflowId,
            workflowNodeName,
        });

        setConnectionId(connectionId);

        if (currentNode) {
            setCurrentNode({...currentNode, connectionId});
        }

        setCurrentConnection(componentConnections?.find((connection) => connection.id === connectionId));

        queryClient.removeQueries({
            queryKey: [...WorkflowNodeDynamicPropertyKeys.workflowNodeDynamicProperties, workflowId],
        });

        queryClient.removeQueries({
            queryKey: [...WorkflowNodeOptionKeys.workflowNodeOptions, workflowId],
        });
    };

    return (
        <div className="flex flex-col gap-6">
            <div className="space-y-1">
                {componentDefinition && (
                    <Label className="mb-2 font-normal">
                        {componentDefinition.title}

                        {workflowConnection.required && <RequiredMark />}
                    </Label>
                )}

                {workflowConnectionsCount > 1 && (
                    <Label className="text-sm text-muted-foreground">{workflowConnection.key}</Label>
                )}

                <Select
                    onValueChange={(value) => handleValueChange(+value, workflowConnection.key)}
                    required={workflowConnection.required}
                    value={connectionId ? connectionId.toString() : undefined}
                >
                    <div className="flex space-x-2">
                        {componentConnections && componentConnections.length > 0 && (
                            <SelectTrigger>
                                <SelectValue placeholder="Choose Connection..." />
                            </SelectTrigger>
                        )}

                        {componentDefinition && (
                            <ConnectionDialog
                                componentDefinition={componentDefinition}
                                connectionTagsQueryKey={ConnectionKeys!.connectionTags}
                                connectionsQueryKey={ConnectionKeys!.connections}
                                triggerNode={
                                    <Button
                                        className={twMerge('mt-auto p-2', !componentConnections?.length && 'w-full')}
                                        title="Create a new connection"
                                        variant="outline"
                                    >
                                        <PlusIcon className="size-5" />

                                        {!componentConnections?.length && 'Create Connection'}
                                    </Button>
                                }
                                useCreateConnectionMutation={useCreateConnectionMutation}
                                useGetConnectionTagsQuery={useGetConnectionTagsQuery!}
                            />
                        )}
                    </div>

                    <SelectContent>
                        {componentConnections &&
                            componentConnections.map((connection) => (
                                <SelectItem key={connection.id} value={connection.id!.toString()}>
                                    <div className="flex items-center space-x-1">
                                        <span>{connection.name}</span>

                                        <span className="text-xs text-gray-500">
                                            {connection?.tags?.map((tag) => tag.name).join(', ')}
                                        </span>

                                        <Badge variant="outline">{connection.environment}</Badge>
                                    </div>
                                </SelectItem>
                            ))}
                    </SelectContent>
                </Select>
            </div>

            {currentConnection && connectionDefinition && (
                <ConnectionParameters
                    authorizationParameters={currentConnection.authorizationParameters}
                    connectionDefinition={connectionDefinition}
                    connectionParameters={currentConnection.connectionParameters}
                />
            )}
        </div>
    );
};

export default ConnectionTabConnectionSelect;
