# KMS

## 问答

1. KMS是什么服务？能解决什么问题

   AWS Key Management Service（KMS）是一个用于创建和管理CMK（customer master key）的管理服务， 加密key可用于加密数据。

2. 使用KMS key的方式有哪些？

   - 已集成到其他服务中，直接在其他服务中使用
   - CLI
   - Programming with KMS API

3. 可以对KMS key进行哪些操作？

   - [Create](https://docs.aws.amazon.com/kms/latest/developerguide/create-keys.html), [edit](https://docs.aws.amazon.com/kms/latest/developerguide/editing-keys.html), and [view](https://docs.aws.amazon.com/kms/latest/developerguide/viewing-keys.html) [symmetric and asymmetric CMKs](https://docs.aws.amazon.com/kms/latest/developerguide/symmetric-asymmetric.html)
   - Control access to your CMKs by using [key policies](https://docs.aws.amazon.com/kms/latest/developerguide/key-policies.html), [IAM policies](https://docs.aws.amazon.com/kms/latest/developerguide/iam-policies.html), and [grants](https://docs.aws.amazon.com/kms/latest/developerguide/grants.html). AWS KMS supports [attribute-based access control](https://docs.aws.amazon.com/kms/latest/developerguide/abac.html) (ABAC). You can also refine policies by using [condition keys](https://docs.aws.amazon.com/kms/latest/developerguide/policy-conditions.html).
   - [Create, delete, list, and update aliases](https://docs.aws.amazon.com/kms/latest/developerguide/kms-alias.html), which are friendly names for your CMKs. You can also [use aliases to control access](https://docs.aws.amazon.com/kms/latest/developerguide/alias-authorization.html) to your CMKs.
   - [Tag your CMKs](https://docs.aws.amazon.com/kms/latest/developerguide/tagging-keys.html) for identification, automation, and cost tracking. You can also [use tags to control access](https://docs.aws.amazon.com/kms/latest/developerguide/tag-authorization.html) to your CMKs.
   - [Enable and disable](https://docs.aws.amazon.com/kms/latest/developerguide/enabling-keys.html) CMKs
   - Enable and disable [automatic rotation](https://docs.aws.amazon.com/kms/latest/developerguide/rotate-keys.html) of the cryptographic material in a CMK
   - [Delete CMKs](https://docs.aws.amazon.com/kms/latest/developerguide/deleting-keys.html) to complete the key lifecycle

## 使用CLI操作KMS

1. 创建对称加密KMS key

   ```shell
   $ aws kms create-key
   {
       "KeyMetadata": {
           "AWSAccountId": "160071257600",
           "KeyId": "1f1708d1-8f8d-4303-bf04-725c66a57be7",
           "Arn": "arn:aws:kms:ap-southeast-2:160071257600:key/1f1708d1-8f8d-4303-bf04-725c66a57be7",
           "CreationDate": "2021-06-11T11:26:54.517000+08:00",
           "Enabled": true,
           "Description": "",
           "KeyUsage": "ENCRYPT_DECRYPT",
           "KeyState": "Enabled",
           "Origin": "AWS_KMS",
           "KeyManager": "CUSTOMER",
           "CustomerMasterKeySpec": "SYMMETRIC_DEFAULT",
           "EncryptionAlgorithms": [
               "SYMMETRIC_DEFAULT"
           ]
       }
   }
   ```

2. 加密

   ```shell
   aws kms encrypt \
       --key-id 1234abcd-12ab-34cd-56ef-1234567890ab \
       --plaintext fileb://ExamplePlaintextFile \
       --output text \
       --query CiphertextBlob | base64 \
       --decode > ExampleEncryptedFile
   ```

3. 解密

   ```shell
   aws kms decrypt \
       --ciphertext-blob fileb://ExampleEncryptedFile \
       --key-id 1234abcd-12ab-34cd-56ef-1234567890ab \
       --output text \
       --query Plaintext | base64 \
       --decode > ExamplePlaintextFile
   ```

   