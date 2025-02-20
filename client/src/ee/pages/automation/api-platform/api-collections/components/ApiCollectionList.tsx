import {Collapsible, CollapsibleContent} from '@/components/ui/collapsible';
import {ApiCollection, Tag} from '@/ee/shared/middleware/automation/api-platform';

import ApiCollectionEndpointList from './ApiCollectionEndpointList';
import ApiCollectionListItem from './ApiCollectionListItem';

const ApiCollectionList = ({apiCollections, tags}: {apiCollections: ApiCollection[]; tags?: Tag[]}) => {
    return (
        <div className="w-full divide-y divide-border/50 px-4 2xl:mx-auto 2xl:w-4/5">
            {apiCollections.length > 0 &&
                apiCollections.map((apiCollection) => {
                    return (
                        <Collapsible className="group" key={apiCollection.id}>
                            <ApiCollectionListItem apiCollection={apiCollection} tags={tags} />

                            <CollapsibleContent>
                                <ApiCollectionEndpointList
                                    apiCollectionEndpoints={apiCollection.endpoints}
                                    apiCollectionId={apiCollection.id!}
                                    collectionVersion={apiCollection.collectionVersion!}
                                    projectDeploymentId={apiCollection.projectDeploymentId!}
                                    projectId={apiCollection.projectId}
                                    projectVersion={apiCollection.projectVersion}
                                />
                            </CollapsibleContent>
                        </Collapsible>
                    );
                })}
        </div>
    );
};

export default ApiCollectionList;
