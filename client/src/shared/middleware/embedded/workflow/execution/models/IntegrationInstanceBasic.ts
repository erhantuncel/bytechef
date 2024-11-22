/* tslint:disable */
/* eslint-disable */
/**
 * Embedded Execution Internal API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from '../runtime';
import type {Environment} from "@/shared/middleware/embedded/configuration";
/**
 * Contains configurations and connections required for the execution of integration workflows for a connected user.
 * @export
 * @interface IntegrationInstanceBasic
 */
export interface IntegrationInstanceBasic {
    /**
     * The id of a connection.
     * @type {number}
     * @memberof IntegrationInstanceBasic
     */
    readonly connectionId: number;
    /**
     * The id of a connected user.
     * @type {number}
     * @memberof IntegrationInstanceBasic
     */
    readonly connectedUserId?: number;
    /**
     * The created by.
     * @type {string}
     * @memberof IntegrationInstanceBasic
     */
    readonly createdBy?: string;
    /**
     * The created date.
     * @type {Date}
     * @memberof IntegrationInstanceBasic
     */
    readonly createdDate?: Date;
    /**
     * If an integration instance is enabled or not.
     * @type {boolean}
     * @memberof IntegrationInstanceBasic
     */
    enabled?: boolean;
    /**
     * The id of an integration instance.
     * @type {number}
     * @memberof IntegrationInstanceBasic
     */
    /**
     *
     * @type {Environment}
     * @memberof IntegrationInstanceBasic
     */
    environment?: Environment;
    readonly id?: number;
    /**
     * The last execution date.
     * @type {Date}
     * @memberof IntegrationInstanceBasic
     */
    readonly lastExecutionDate?: Date;
    /**
     * The last modified by.
     * @type {string}
     * @memberof IntegrationInstanceBasic
     */
    readonly lastModifiedBy?: string;
    /**
     * The last modified date.
     * @type {Date}
     * @memberof IntegrationInstanceBasic
     */
    readonly lastModifiedDate?: Date;
    /**
     * Th id of an integration instance configuration.
     * @type {number}
     * @memberof IntegrationInstanceBasic
     */
    integrationInstanceConfigurationId?: number;
}

/**
 * Check if a given object implements the IntegrationInstanceBasic interface.
 */
export function instanceOfIntegrationInstanceBasic(value: object): value is IntegrationInstanceBasic {
    if (!('connectionId' in value) || value['connectionId'] === undefined) return false;
    return true;
}

export function IntegrationInstanceBasicFromJSON(json: any): IntegrationInstanceBasic {
    return IntegrationInstanceBasicFromJSONTyped(json, false);
}

export function IntegrationInstanceBasicFromJSONTyped(json: any, ignoreDiscriminator: boolean): IntegrationInstanceBasic {
    if (json == null) {
        return json;
    }
    return {

        'connectionId': json['connectionId'],
        'connectedUserId': json['connectedUserId'] == null ? undefined : json['connectedUserId'],
        'createdBy': json['createdBy'] == null ? undefined : json['createdBy'],
        'createdDate': json['createdDate'] == null ? undefined : (new Date(json['createdDate'])),
        'enabled': json['enabled'] == null ? undefined : json['enabled'],
        'id': json['id'] == null ? undefined : json['id'],
        'lastExecutionDate': json['lastExecutionDate'] == null ? undefined : (new Date(json['lastExecutionDate'])),
        'lastModifiedBy': json['lastModifiedBy'] == null ? undefined : json['lastModifiedBy'],
        'lastModifiedDate': json['lastModifiedDate'] == null ? undefined : (new Date(json['lastModifiedDate'])),
        'integrationInstanceConfigurationId': json['integrationInstanceConfigurationId'] == null ? undefined : json['integrationInstanceConfigurationId'],
    };
}

  export function IntegrationInstanceBasicToJSON(json: any): IntegrationInstanceBasic {
      return IntegrationInstanceBasicToJSONTyped(json, false);
  }

  export function IntegrationInstanceBasicToJSONTyped(value?: Omit<IntegrationInstanceBasic, 'connectionId'|'connectedUserId'|'createdBy'|'createdDate'|'id'|'lastExecutionDate'|'lastModifiedBy'|'lastModifiedDate'> | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {

        'enabled': value['enabled'],
        'integrationInstanceConfigurationId': value['integrationInstanceConfigurationId'],
    };
}

