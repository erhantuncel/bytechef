import {Collapsible, CollapsibleContent, CollapsibleTrigger} from '@/components/ui/collapsible';
import {PropertyAllType} from '@/shared/types';
import {ChevronDownIcon} from 'lucide-react';
import {Fragment} from 'react';
import {FieldValues} from 'react-hook-form/dist/types';
import {Control, FormState} from 'react-hook-form/dist/types/form';
import {twMerge} from 'tailwind-merge';

import useWorkflowNodeDetailsPanelStore from '../../stores/useWorkflowNodeDetailsPanelStore';
import Property from './Property';

interface PropertiesProps {
    /* eslint-disable @typescript-eslint/no-explicit-any */
    control?: Control<any, any>;
    controlPath?: string;
    customClassName?: string;
    operationName?: string;
    formState?: FormState<FieldValues>;
    path?: string;
    properties: Array<PropertyAllType>;
}

const Properties = ({
    control,
    controlPath,
    customClassName,
    formState,
    operationName,
    path,
    properties,
}: PropertiesProps) => {
    const {currentNode} = useWorkflowNodeDetailsPanelStore();

    const advancedProperties = properties.filter((property) => {
        const {advancedOption, hidden, name} = property;

        if (!name || !advancedOption || hidden) {
            return false;
        }

        return true;
    });

    const simpleProperties = properties.filter(
        (property) => property.name && !property.advancedOption && !property.hidden
    );

    return (
        <>
            <ul
                className={twMerge('space-y-4', customClassName)}
                key={`${currentNode?.workflowNodeName}_${currentNode?.operationName}_properties`}
            >
                {simpleProperties.map((property, index) => (
                    <Property
                        control={control}
                        controlPath={controlPath}
                        formState={formState}
                        key={`${currentNode?.workflowNodeName}_${currentNode?.operationName}_${property.name}_${index}`}
                        operationName={operationName}
                        path={path}
                        property={property}
                    />
                ))}
            </ul>

            {!!advancedProperties.length && (
                <Collapsible className="group flex w-full flex-col justify-center">
                    <CollapsibleTrigger className="mx-4 flex items-center justify-center rounded-md px-4 py-2 text-center hover:bg-gray-100">
                        <h2 className="text-sm text-gray-500">Advanced Properties</h2>

                        <ChevronDownIcon className="ml-2 size-4 text-gray-500 transition-all group-data-[state=open]:rotate-180" />
                    </CollapsibleTrigger>

                    <CollapsibleContent>
                        <ul className="space-y-4 p-4" key={`${currentNode?.operationName}_advancedProperties`}>
                            {advancedProperties.map((property, index) => (
                                <Property
                                    control={control}
                                    controlPath={controlPath}
                                    formState={formState}
                                    key={`${property.name}_${currentNode?.operationName}_${index}`}
                                    operationName={operationName}
                                    path={path}
                                    property={property}
                                />
                            ))}
                        </ul>
                    </CollapsibleContent>
                </Collapsible>
            )}
        </>
    );
};

export default Properties;
