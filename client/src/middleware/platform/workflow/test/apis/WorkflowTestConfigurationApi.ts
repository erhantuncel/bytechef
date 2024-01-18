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


import * as runtime from '../runtime';
import type {
  UpdateWorkflowTestConfigurationConnectionRequestModel,
  UpdateWorkflowTestConfigurationInputsRequestModel,
  WorkflowTestConfigurationConnectionModel,
  WorkflowTestConfigurationModel,
} from '../models/index';
import {
    UpdateWorkflowTestConfigurationConnectionRequestModelFromJSON,
    UpdateWorkflowTestConfigurationConnectionRequestModelToJSON,
    UpdateWorkflowTestConfigurationInputsRequestModelFromJSON,
    UpdateWorkflowTestConfigurationInputsRequestModelToJSON,
    WorkflowTestConfigurationConnectionModelFromJSON,
    WorkflowTestConfigurationConnectionModelToJSON,
    WorkflowTestConfigurationModelFromJSON,
    WorkflowTestConfigurationModelToJSON,
} from '../models/index';

export interface CreateWorkflowTestConfigurationRequest {
    workflowTestConfigurationModel: WorkflowTestConfigurationModel;
}

export interface GetWorkflowTestConfigurationRequest {
    id: number;
}

export interface GetWorkflowTestConfigurationConnectionsRequest {
    workflowId: string;
    operationName: string;
}

export interface UpdateWorkflowTestConfigurationRequest {
    id: number;
    workflowTestConfigurationModel: WorkflowTestConfigurationModel;
}

export interface UpdateWorkflowTestConfigurationConnectionRequest {
    workflowId: string;
    operationName: string;
    key: string;
    updateWorkflowTestConfigurationConnectionRequestModel: UpdateWorkflowTestConfigurationConnectionRequestModel;
}

export interface UpdateWorkflowTestConfigurationInputsRequest {
    workflowId: string;
    updateWorkflowTestConfigurationInputsRequestModel: UpdateWorkflowTestConfigurationInputsRequestModel;
}

/**
 * 
 */
export class WorkflowTestConfigurationApi extends runtime.BaseAPI {

    /**
     * Create a new workflow test configuration.
     * Create a new workflow test configuration.
     */
    async createWorkflowTestConfigurationRaw(requestParameters: CreateWorkflowTestConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<WorkflowTestConfigurationModel>> {
        if (requestParameters.workflowTestConfigurationModel === null || requestParameters.workflowTestConfigurationModel === undefined) {
            throw new runtime.RequiredError('workflowTestConfigurationModel','Required parameter requestParameters.workflowTestConfigurationModel was null or undefined when calling createWorkflowTestConfiguration.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/workflow-test-configurations`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: WorkflowTestConfigurationModelToJSON(requestParameters.workflowTestConfigurationModel),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => WorkflowTestConfigurationModelFromJSON(jsonValue));
    }

    /**
     * Create a new workflow test configuration.
     * Create a new workflow test configuration.
     */
    async createWorkflowTestConfiguration(requestParameters: CreateWorkflowTestConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<WorkflowTestConfigurationModel> {
        const response = await this.createWorkflowTestConfigurationRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Get a workflow test configuration.
     * Get a workflow test configuration
     */
    async getWorkflowTestConfigurationRaw(requestParameters: GetWorkflowTestConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<WorkflowTestConfigurationModel>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling getWorkflowTestConfiguration.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/workflow-test-configurations/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => WorkflowTestConfigurationModelFromJSON(jsonValue));
    }

    /**
     * Get a workflow test configuration.
     * Get a workflow test configuration
     */
    async getWorkflowTestConfiguration(requestParameters: GetWorkflowTestConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<WorkflowTestConfigurationModel> {
        const response = await this.getWorkflowTestConfigurationRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Get a workflow test configuration connections.
     * Get a workflow test configuration connections
     */
    async getWorkflowTestConfigurationConnectionsRaw(requestParameters: GetWorkflowTestConfigurationConnectionsRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Array<WorkflowTestConfigurationConnectionModel>>> {
        if (requestParameters.workflowId === null || requestParameters.workflowId === undefined) {
            throw new runtime.RequiredError('workflowId','Required parameter requestParameters.workflowId was null or undefined when calling getWorkflowTestConfigurationConnections.');
        }

        if (requestParameters.operationName === null || requestParameters.operationName === undefined) {
            throw new runtime.RequiredError('operationName','Required parameter requestParameters.operationName was null or undefined when calling getWorkflowTestConfigurationConnections.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/workflow-test-configurations/{workflowId}/connections/{operationName}`.replace(`{${"workflowId"}}`, encodeURIComponent(String(requestParameters.workflowId))).replace(`{${"operationName"}}`, encodeURIComponent(String(requestParameters.operationName))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(WorkflowTestConfigurationConnectionModelFromJSON));
    }

    /**
     * Get a workflow test configuration connections.
     * Get a workflow test configuration connections
     */
    async getWorkflowTestConfigurationConnections(requestParameters: GetWorkflowTestConfigurationConnectionsRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Array<WorkflowTestConfigurationConnectionModel>> {
        const response = await this.getWorkflowTestConfigurationConnectionsRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Get all workflow test configurations.
     * Get all workflow test configurations
     */
    async getWorkflowTestConfigurationsRaw(initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<Array<WorkflowTestConfigurationModel>>> {
        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/workflow-test-configurations`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(WorkflowTestConfigurationModelFromJSON));
    }

    /**
     * Get all workflow test configurations.
     * Get all workflow test configurations
     */
    async getWorkflowTestConfigurations(initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<Array<WorkflowTestConfigurationModel>> {
        const response = await this.getWorkflowTestConfigurationsRaw(initOverrides);
        return await response.value();
    }

    /**
     * Update an existing workflow test configuration.
     * Update an existing workflow test configuration
     */
    async updateWorkflowTestConfigurationRaw(requestParameters: UpdateWorkflowTestConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<WorkflowTestConfigurationModel>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling updateWorkflowTestConfiguration.');
        }

        if (requestParameters.workflowTestConfigurationModel === null || requestParameters.workflowTestConfigurationModel === undefined) {
            throw new runtime.RequiredError('workflowTestConfigurationModel','Required parameter requestParameters.workflowTestConfigurationModel was null or undefined when calling updateWorkflowTestConfiguration.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/workflow-test-configurations/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: WorkflowTestConfigurationModelToJSON(requestParameters.workflowTestConfigurationModel),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => WorkflowTestConfigurationModelFromJSON(jsonValue));
    }

    /**
     * Update an existing workflow test configuration.
     * Update an existing workflow test configuration
     */
    async updateWorkflowTestConfiguration(requestParameters: UpdateWorkflowTestConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<WorkflowTestConfigurationModel> {
        const response = await this.updateWorkflowTestConfigurationRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Update a workflow test configuration connection.
     * Update a workflow test configuration connection
     */
    async updateWorkflowTestConfigurationConnectionRaw(requestParameters: UpdateWorkflowTestConfigurationConnectionRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<void>> {
        if (requestParameters.workflowId === null || requestParameters.workflowId === undefined) {
            throw new runtime.RequiredError('workflowId','Required parameter requestParameters.workflowId was null or undefined when calling updateWorkflowTestConfigurationConnection.');
        }

        if (requestParameters.operationName === null || requestParameters.operationName === undefined) {
            throw new runtime.RequiredError('operationName','Required parameter requestParameters.operationName was null or undefined when calling updateWorkflowTestConfigurationConnection.');
        }

        if (requestParameters.key === null || requestParameters.key === undefined) {
            throw new runtime.RequiredError('key','Required parameter requestParameters.key was null or undefined when calling updateWorkflowTestConfigurationConnection.');
        }

        if (requestParameters.updateWorkflowTestConfigurationConnectionRequestModel === null || requestParameters.updateWorkflowTestConfigurationConnectionRequestModel === undefined) {
            throw new runtime.RequiredError('updateWorkflowTestConfigurationConnectionRequestModel','Required parameter requestParameters.updateWorkflowTestConfigurationConnectionRequestModel was null or undefined when calling updateWorkflowTestConfigurationConnection.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/workflow-test-configurations/{workflowId}/connections/{operationName}/{key}`.replace(`{${"workflowId"}}`, encodeURIComponent(String(requestParameters.workflowId))).replace(`{${"operationName"}}`, encodeURIComponent(String(requestParameters.operationName))).replace(`{${"key"}}`, encodeURIComponent(String(requestParameters.key))),
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: UpdateWorkflowTestConfigurationConnectionRequestModelToJSON(requestParameters.updateWorkflowTestConfigurationConnectionRequestModel),
        }, initOverrides);

        return new runtime.VoidApiResponse(response);
    }

    /**
     * Update a workflow test configuration connection.
     * Update a workflow test configuration connection
     */
    async updateWorkflowTestConfigurationConnection(requestParameters: UpdateWorkflowTestConfigurationConnectionRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<void> {
        await this.updateWorkflowTestConfigurationConnectionRaw(requestParameters, initOverrides);
    }

    /**
     * Update a workflow test configuration inputs.
     * Update a workflow test configuration inputs
     */
    async updateWorkflowTestConfigurationInputsRaw(requestParameters: UpdateWorkflowTestConfigurationInputsRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<void>> {
        if (requestParameters.workflowId === null || requestParameters.workflowId === undefined) {
            throw new runtime.RequiredError('workflowId','Required parameter requestParameters.workflowId was null or undefined when calling updateWorkflowTestConfigurationInputs.');
        }

        if (requestParameters.updateWorkflowTestConfigurationInputsRequestModel === null || requestParameters.updateWorkflowTestConfigurationInputsRequestModel === undefined) {
            throw new runtime.RequiredError('updateWorkflowTestConfigurationInputsRequestModel','Required parameter requestParameters.updateWorkflowTestConfigurationInputsRequestModel was null or undefined when calling updateWorkflowTestConfigurationInputs.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/workflow-test-configurations/{workflowId}/inputs`.replace(`{${"workflowId"}}`, encodeURIComponent(String(requestParameters.workflowId))),
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: UpdateWorkflowTestConfigurationInputsRequestModelToJSON(requestParameters.updateWorkflowTestConfigurationInputsRequestModel),
        }, initOverrides);

        return new runtime.VoidApiResponse(response);
    }

    /**
     * Update a workflow test configuration inputs.
     * Update a workflow test configuration inputs
     */
    async updateWorkflowTestConfigurationInputs(requestParameters: UpdateWorkflowTestConfigurationInputsRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<void> {
        await this.updateWorkflowTestConfigurationInputsRaw(requestParameters, initOverrides);
    }

}
