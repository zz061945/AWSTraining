# AWS Lambda

## 作业：

1. What is serverless？

   Serverless is a way to describe the services, practices, and strategies that enable you to build more agile applications so you can innovate and respond to change faster. [Ref](https://aws.amazon.com/serverless/)

2. Create lambda fumction with AWS Web Console and test.

   Create lambda following [Ref](https://aws.amazon.com/cn/getting-started/hands-on/run-serverless-code/)

   Code: 

   ```js
   console.log('Loading function');
   
   exports.handler = async (event, context) => {
       //console.log('Received event:', JSON.stringify(event, null, 2));
       console.log('value1 =', event.key1);
       console.log('value2 =', event.key2);
       console.log('value3 =', event.key3);
       return event.key1;  // Echo back the first key value
       // throw new Error('Something went wrong');
   };
   
   ```

   Test data:

   ```json
   {
     "key1": "Test Lambda",
     "key2": "Zhang Zhen",
     "key3": "Thu May 27 18:36"
   }
   ```

   Execute result log:

   ```
   START RequestId: c816c61b-afaa-4220-9bd9-b3a9fb05d325 Version: $LATEST
   2021-05-27T10:40:28.763Z	c816c61b-afaa-4220-9bd9-b3a9fb05d325	INFO	value1 = Zhang Zhen
   2021-05-27T10:40:28.783Z	c816c61b-afaa-4220-9bd9-b3a9fb05d325	INFO	value2 = Test AWS Lambda
   2021-05-27T10:40:28.783Z	c816c61b-afaa-4220-9bd9-b3a9fb05d325	INFO	value3 = Thu May 27 18:40
   END RequestId: c816c61b-afaa-4220-9bd9-b3a9fb05d325
   REPORT RequestId: c816c61b-afaa-4220-9bd9-b3a9fb05d325	Duration: 40.38 ms	Billed Duration: 41 ms	Memory Size: 128 MB	Max Memory Used: 64 MB	
   ```

3. Lambda function via aws cli with ZIP file and invoke Create lambda

   3.1 Login aws with saml2aws:

   ```shell
   $ saml2aws login --profile tw-aws-beach             
   Using IDP Account default to access Okta https://thoughtworks.okta.com/home/amazon_aws/xxx
   To use saved password just hit enter.
   ? Username 
   ? Password
   
   Authenticating as xxx@thoughtworks.com ...
   ? Select which MFA option to use TOTP MFA authentication
   ? Enter verification code
   Selected role: 
   Requesting AWS credentials using SAML assertion
   Logged in as: 
   
   Your new access key pair has been stored in the AWS configuration
   Note that it will expire at 2021-05-27 19:46:35 +0800 CST
   To use this credential, call the AWS CLI with the --profile option (e.g. aws --profile tw-aws-beach ec2 describe-instances).
   ```

   3.2 Create execution rule
   
   ```shell
   aws iam create-role --role-name lambda-ex-zhangzhen-cli  --profile tw-aws-beach --assume-role-policy-document file://trust-policy.json
   ```
   
   trust-policy.json: 
   
   ```json
   {
     "Version": "2012-10-17",
     "Statement": [
       {
         "Effect": "Allow",
         "Principal": {
           "Service": "lambda.amazonaws.com"
         },
         "Action": "sts:AssumeRole"
       }
     ]
   }
   ```
   
   Output:
   
   ```json
   {
       "Role": {
           "Path": "/",
           "RoleName": "lambda-ex-zhangzhen-cli",
           "RoleId": "xxxx",
           "Arn": "xxxxxx",
           "CreateDate": "2021-06-02T10:46:07+00:00",
           "AssumeRolePolicyDocument": {
               "Version": "2012-10-17",
               "Statement": [
                   {
                       "Effect": "Allow",
                       "Principal": {
                           "Service": "lambda.amazonaws.com"
                       },
                       "Action": "sts:AssumeRole"
                   }
               ]
           }
       }
   }
   ```
   
   3.3 Add permission to the rule:
   
   ```shell
   aws iam attach-role-policy --role-name lambda-ex-zhangzhen-cli --policy-arn arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole --profile tw-aws-beach
   ```
   
   3.4 Create function
   
   ```js
   exports.handler = async function(event, context) {
     console.log("ENVIRONMENT VARIABLES\n" + JSON.stringify(process.env, null, 2))
     console.log("EVENT\n" + JSON.stringify(event, null, 2))
     console.log("Zhang Zhen created lambda with aws-cli")
     return context.logStreamName
   }
   ```
   
   3.5 Create deployment package:
   
   ```shell
   zip function.zip index.js
   ```
   
   3.6 Create a lambda function
   
   ```shell
   aws lambda create-function --function-name lambda-zhangzhen-cli --zip-file fileb://function.zip --handler index.handler --runtime nodejs12.x --role arn:aws:iam::160071257600:role/lambda-ex-zhangzhen-cli --profile tw-aws-beach
   ```
   
   Output:
   
   ```json
   {
       "FunctionName": "lambda-zhangzhen-cli",
       "FunctionArn": "xxxxxx",
       "Runtime": "nodejs12.x",
       "Role": "xxxx",
       "Handler": "index.handler",
       "CodeSize": 352,
       "Description": "",
       "Timeout": 3,
       "MemorySize": 128,
       "LastModified": "2021-06-02T10:56:37.296+0000",
       "CodeSha256": "0/2qiMmsWWxF4XwNGYdS1BKPegq0bVILkJD5eekqwBQ=",
       "Version": "$LATEST",
       "TracingConfig": {
           "Mode": "PassThrough"
       },
       "RevisionId": "3dc8c027-18d6-48b3-aad5-839a80553b98",
       "State": "Active",
       "LastUpdateStatus": "Successful",
       "PackageType": "Zip"
   }
   ```
   
   3.7 Execute lambda:
   
   ```shell
   aws lambda invoke --function-name lambda-zhangzhen-cli out --log-type Tail --profile tw-aws-beach
   ```
   
   Output:
   
   ```json
   {
       "StatusCode": 200,
       "LogResult": "U1RBUlQgUmVxdWVzdElkOiBmMTg1ZjgxYS0yZThlLTQxMjEtOWU4YS05NzBlZmI2NmM0ZWYgVmVyc2lvbjogJExBVEVT...",
       "ExecutedVersion": "$LATEST"
   }
   ```
   
   Decode output:
   
   ```shell
   aws lambda invoke --function-name lambda-zhangzhen-cli out --log-type Tail --profile tw-aws-beach --query 'LogResult' --output text | base64 -d
   ```
   
   Output:
   
   ```shell
   START RequestId: 6849bb1d-3180-46fa-8a89-86cacec74c40 Version: $LATEST
   2021-06-02T11:06:53.230Z	6849bb1d-3180-46fa-8a89-86cacec74c40	INFO	ENVIRONMENT VARIABLES
   {
     ...
   }
   2021-06-02T11:06:53.230Z	6849bb1d-3180-46fa-8a89-86cacec74c40	INFO	EVENT
   {}
   2021-06-02T11:06:53.230Z	6849bb1d-3180-46fa-8a89-86cacec74c40	INFO	Zhang Zhen created lambda with aws-cli
   END RequestId: 6849bb1d-3180-46fa-8a89-86cacec74c40
   REPORT RequestId: 6849bb1d-3180-46fa-8a89-86cacec74c40	Duration: 38.59 ms	Billed Duration: 39 ms	Memory Size: 128 MB	Max Memory Used: 65 MB
   ```
   
   3.8 List lambda functions
   
   ```shell
   aws lambda list-functions --max-items 10
   ```
   
   3.9 Retrieve a Lambda function
   
   ```shell
   aws lambda get-function --function-name lambda-zhangzhen-cli
   ```
   
   3.10 Clean up
   
   ```shell
   aws lambda delete-function --function-name lambda-zhangzhen-cli
   ```
   
4. Create Lambda by aws cloudformation

   1. Create a role in IAM with policy:

      ```json
      {
          "Version": "2012-10-17",
          "Statement": [
              {
                  "Effect": "Allow",
                  "Action": "logs:CreateLogGroup",
                  "Resource": "arn:aws:logs:ap-southeast-2:160071257600:*"
              },
              {
                  "Effect": "Allow",
                  "Action": [
                      "logs:CreateLogStream",
                      "logs:PutLogEvents"
                  ],
                  "Resource": "*"
              }
          ]
      }
      ```

   2. Create stack with template file [lambda-cloudformation-template-zhen.yaml](./lambda-cloudformation-template-zhen.yaml)

## 遇到的问题及解决方案：

1. 无法找到credential的问题

   ```shell
   $ aws iam create-role --role-name lambda-ex-zhangzhen-cli --assume-role-policy-document trust-policy.json
   
   Unable to locate credentials. You can configure credentials by running "aws configure".
   ```

   解决方法：

   - 显示指定profile：

     ```shell
     $ aws iam create-role --role-name lambda-ex-zhangzhen-cli --profile tw-aws-beach --assume-role-policy-document trust-policy.json
     ```

   - 设置环境变量指定默认profile：

     ```shell
     $ export AWS_DEFAULT_PROFILE=tw-aws-beach
     ```

     

