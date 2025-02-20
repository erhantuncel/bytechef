---
title: "Webhook"
description: "Webhook is a method utilized by applications to supply real-time information to other apps. Such a process usually delivers data immediately as and when it occurs. Webhook Trigger enables users to receive callouts whenever a service provides the option of distributing signals to a user-defined URL."
---
## Reference
<hr />

Webhook is a method utilized by applications to supply real-time information to other apps. Such a process usually delivers data immediately as and when it occurs. Webhook Trigger enables users to receive callouts whenever a service provides the option of distributing signals to a user-defined URL.


Categories: [helpers]


Version: 1

<hr />




## Triggers


### Auto Respond with HTTP 200 Status
The webhook trigger always replies immediately with an HTTP 200 status code in response to any incoming webhook request. This guarantees execution of the webhook trigger, but does not involve any validation of the received request.

#### Type: STATIC_WEBHOOK
#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
null


### Output


___Sample Output:___

```{headers={}, method=POST, parameters={}}```


null




### Validate and Respond
Upon receiving a webhook request, it goes through a validation process. Once validated, the webhook trigger responds to the sender with an appropriate HTTP status code.

#### Type: STATIC_WEBHOOK
#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| CSRF Token | STRING | TEXT  |  To trigger the workflow successfully, the security token must match the X-Csrf-Token HTTP header value passed by the client.  |


### Output


___Sample Output:___

```{headers={}, method=POST, parameters={}}```


null




### Await Workflow and Respond
You have the flexibility to set up your preferred response. After a webhook request is received, the webhook trigger enters a waiting state for the workflow's response.

#### Type: STATIC_WEBHOOK
#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| CSRF Token | STRING | TEXT  |  To trigger the workflow successfully, the security token must match the X-Csrf-Token HTTP header value passed by the client.  |
| Timeout (ms) | INTEGER | INTEGER  |  The incoming request will time out after the specified number of milliseconds. The max wait time before a timeout is 5 minutes.  |


### Output


___Sample Output:___

```{headers={}, method=POST, parameters={}}```


null




<hr />



## Actions


### Response to Webhook Request
Converts the response to the webhook request.

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Response Type | STRING | SELECT  |  The type of the response.  |
| Headers | {} | OBJECT_BUILDER  |  The headers of the response.  |
| Body | {} | OBJECT_BUILDER  |  The body of the response.  |
| Body | STRING | TEXT  |  The body of the response.  |
| Redirect URL | STRING | TEXT  |  The redirect URL.  |
| Body | FILE_ENTRY | FILE_ENTRY  |  The body of the response.  |
| Status Code | INTEGER | INTEGER  |  The status code of the response.  |




