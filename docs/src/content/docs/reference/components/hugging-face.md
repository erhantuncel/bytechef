---
title: "Hugging Face"
description: "Hugging Face is on a journey to advance and democratize artificial intelligence through open source and open science."
---
## Reference
<hr />

Hugging Face is on a journey to advance and democratize artificial intelligence through open source and open science.


Categories: [artificial-intelligence]


Version: 1

<hr />



## Connections

Version: 1


### Bearer Token

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| Token | STRING | TEXT  |  |





<hr />





## Actions


### Ask
Ask anything you want.

#### Properties

|      Name      |     Type     |     Control Type     |     Description     |
|:--------------:|:------------:|:--------------------:|:-------------------:|
| URL | STRING | TEXT  |  Url of the inference endpoint  |
| Messages | [{STRING\(role), STRING\(content), [FILE_ENTRY]\(attachments)}] | ARRAY_BUILDER  |  A list of messages comprising the conversation so far.  |
| Response | {STRING\(responseFormat), STRING\(responseSchema)} | OBJECT_BUILDER  |  The response from the API.  |




