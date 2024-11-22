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
import type { ExecutionError } from './ExecutionError';
import {
    ExecutionErrorFromJSON,
    ExecutionErrorFromJSONTyped,
    ExecutionErrorToJSON,
    ExecutionErrorToJSONTyped,
} from './ExecutionError';
import type { WorkflowTask } from './WorkflowTask';
import {
    WorkflowTaskFromJSON,
    WorkflowTaskFromJSONTyped,
    WorkflowTaskToJSON,
    WorkflowTaskToJSONTyped,
} from './WorkflowTask';

/**
 * Adds execution semantics to a task.
 * @export
 * @interface TaskExecution
 */
export interface TaskExecution {
    /**
     * The created by.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly createdBy?: string;
    /**
     * The created date.
     * @type {Date}
     * @memberof TaskExecution
     */
    readonly createdDate?: Date;
    /**
     * The time when a task instance ended (CANCELLED, FAILED, COMPLETED).
     * @type {Date}
     * @memberof TaskExecution
     */
    readonly endDate?: Date;
    /**
     * 
     * @type {ExecutionError}
     * @memberof TaskExecution
     */
    error?: ExecutionError;
    /**
     * The total time in ms for a task to execute (excluding wait time of the task in transit). i.e. actual execution time on a worker node.
     * @type {number}
     * @memberof TaskExecution
     */
    readonly executionTime?: number;
    /**
     * The icon of the task.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly icon?: string;
    /**
     * The id of a task execution.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly id?: string;
    /**
     * The input parameters for a task.
     * @type {{ [key: string]: any; }}
     * @memberof TaskExecution
     */
    readonly input?: { [key: string]: any; };
    /**
     * The id of a job for which a task belongs to.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly jobId: string;
    /**
     * The last modified by.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly lastModifiedBy?: string;
    /**
     * The last modified date.
     * @type {Date}
     * @memberof TaskExecution
     */
    readonly lastModifiedDate?: Date;
    /**
     * The maximum number of times that a task may retry.
     * @type {number}
     * @memberof TaskExecution
     */
    readonly maxRetries?: number;
    /**
     * The result output generated by the task handler which executed a task.
     * @type {object}
     * @memberof TaskExecution
     */
    readonly output?: object;
    /**
     * The id of the parent task, if this is a sub-task.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly parentId?: string;
    /**
     * The priority value.
     * @type {number}
     * @memberof TaskExecution
     */
    readonly priority: number;
    /**
     * The current progress value, a number between 0 and 100.
     * @type {number}
     * @memberof TaskExecution
     */
    readonly progress?: number;
    /**
     * The number of times that a task has been retried.
     * @type {number}
     * @memberof TaskExecution
     */
    readonly retryAttempts?: number;
    /**
     * The delay to introduce between each retry. Values are to be specified using the ISO-8601 format (excluding the PT prefix). e.g. 10s (ten seconds), 1m (one minute) etc.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly retryDelay?: string;
    /**
     * The factor to use in order to calculate the actual delay time between each successive retry -- multiplying by the value of the retryDelay.
     * @type {number}
     * @memberof TaskExecution
     */
    readonly retryDelayFactor?: number;
    /**
     * The time when a task instance was started.
     * @type {Date}
     * @memberof TaskExecution
     */
    readonly startDate: Date;
    /**
     * The current status of a task.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly status: TaskExecutionStatusEnum;
    /**
     * The numeric order of the task in the workflow.
     * @type {number}
     * @memberof TaskExecution
     */
    readonly taskNumber?: number;
    /**
     * The title of the task.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly title?: string;
    /**
     * The calculated retry delay. i.e. delay * retryAttempts * retryDelayFactor.
     * @type {number}
     * @memberof TaskExecution
     */
    readonly retryDelayMillis?: number;
    /**
     * 
     * @type {WorkflowTask}
     * @memberof TaskExecution
     */
    workflowTask?: WorkflowTask;
    /**
     * The type of the task.
     * @type {string}
     * @memberof TaskExecution
     */
    readonly type?: string;
}


/**
 * @export
 */
export const TaskExecutionStatusEnum = {
    Created: 'CREATED',
    Started: 'STARTED',
    Failed: 'FAILED',
    Cancelled: 'CANCELLED',
    Completed: 'COMPLETED'
} as const;
export type TaskExecutionStatusEnum = typeof TaskExecutionStatusEnum[keyof typeof TaskExecutionStatusEnum];


/**
 * Check if a given object implements the TaskExecution interface.
 */
export function instanceOfTaskExecution(value: object): value is TaskExecution {
    if (!('jobId' in value) || value['jobId'] === undefined) return false;
    if (!('priority' in value) || value['priority'] === undefined) return false;
    if (!('startDate' in value) || value['startDate'] === undefined) return false;
    if (!('status' in value) || value['status'] === undefined) return false;
    return true;
}

export function TaskExecutionFromJSON(json: any): TaskExecution {
    return TaskExecutionFromJSONTyped(json, false);
}

export function TaskExecutionFromJSONTyped(json: any, ignoreDiscriminator: boolean): TaskExecution {
    if (json == null) {
        return json;
    }
    return {
        
        'createdBy': json['createdBy'] == null ? undefined : json['createdBy'],
        'createdDate': json['createdDate'] == null ? undefined : (new Date(json['createdDate'])),
        'endDate': json['endDate'] == null ? undefined : (new Date(json['endDate'])),
        'error': json['error'] == null ? undefined : ExecutionErrorFromJSON(json['error']),
        'executionTime': json['executionTime'] == null ? undefined : json['executionTime'],
        'icon': json['icon'] == null ? undefined : json['icon'],
        'id': json['id'] == null ? undefined : json['id'],
        'input': json['input'] == null ? undefined : json['input'],
        'jobId': json['jobId'],
        'lastModifiedBy': json['lastModifiedBy'] == null ? undefined : json['lastModifiedBy'],
        'lastModifiedDate': json['lastModifiedDate'] == null ? undefined : (new Date(json['lastModifiedDate'])),
        'maxRetries': json['maxRetries'] == null ? undefined : json['maxRetries'],
        'output': json['output'] == null ? undefined : json['output'],
        'parentId': json['parentId'] == null ? undefined : json['parentId'],
        'priority': json['priority'],
        'progress': json['progress'] == null ? undefined : json['progress'],
        'retryAttempts': json['retryAttempts'] == null ? undefined : json['retryAttempts'],
        'retryDelay': json['retryDelay'] == null ? undefined : json['retryDelay'],
        'retryDelayFactor': json['retryDelayFactor'] == null ? undefined : json['retryDelayFactor'],
        'startDate': (new Date(json['startDate'])),
        'status': json['status'],
        'taskNumber': json['taskNumber'] == null ? undefined : json['taskNumber'],
        'title': json['title'] == null ? undefined : json['title'],
        'retryDelayMillis': json['retryDelayMillis'] == null ? undefined : json['retryDelayMillis'],
        'workflowTask': json['workflowTask'] == null ? undefined : WorkflowTaskFromJSON(json['workflowTask']),
        'type': json['type'] == null ? undefined : json['type'],
    };
}

  export function TaskExecutionToJSON(json: any): TaskExecution {
      return TaskExecutionToJSONTyped(json, false);
  }

  export function TaskExecutionToJSONTyped(value?: Omit<TaskExecution, 'createdBy'|'createdDate'|'endDate'|'executionTime'|'icon'|'id'|'input'|'jobId'|'lastModifiedBy'|'lastModifiedDate'|'maxRetries'|'output'|'parentId'|'priority'|'progress'|'retryAttempts'|'retryDelay'|'retryDelayFactor'|'startDate'|'status'|'taskNumber'|'title'|'retryDelayMillis'|'type'> | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {
        
        'error': ExecutionErrorToJSON(value['error']),
        'workflowTask': WorkflowTaskToJSON(value['workflowTask']),
    };
}

