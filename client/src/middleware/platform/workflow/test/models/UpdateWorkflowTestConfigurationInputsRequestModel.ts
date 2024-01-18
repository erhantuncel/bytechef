/* tslint:disable */
/* eslint-disable */
/**
 * The Platform Workflow Test API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { exists, mapValues } from '../runtime';
/**
 * 
 * @export
 * @interface UpdateWorkflowTestConfigurationInputsRequestModel
 */
export interface UpdateWorkflowTestConfigurationInputsRequestModel {
    /**
     * The input parameters used as workflow input values.
     * @type {{ [key: string]: object; }}
     * @memberof UpdateWorkflowTestConfigurationInputsRequestModel
     */
    inputs?: { [key: string]: object; };
}

/**
 * Check if a given object implements the UpdateWorkflowTestConfigurationInputsRequestModel interface.
 */
export function instanceOfUpdateWorkflowTestConfigurationInputsRequestModel(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function UpdateWorkflowTestConfigurationInputsRequestModelFromJSON(json: any): UpdateWorkflowTestConfigurationInputsRequestModel {
    return UpdateWorkflowTestConfigurationInputsRequestModelFromJSONTyped(json, false);
}

export function UpdateWorkflowTestConfigurationInputsRequestModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): UpdateWorkflowTestConfigurationInputsRequestModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'inputs': !exists(json, 'inputs') ? undefined : json['inputs'],
    };
}

export function UpdateWorkflowTestConfigurationInputsRequestModelToJSON(value?: UpdateWorkflowTestConfigurationInputsRequestModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'inputs': value.inputs,
    };
}

