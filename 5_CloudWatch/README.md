# CloudWatch

## 问答题

1. CloudWatch 是什么？我们为什么要使用CloudWatch？

   > Amazon CloudWatch 是一种面向开发运营工程师、开发人员、站点可靠性工程师 (SRE) 和 IT 经理的监控和可观测性服务。
   >
   > CloudWatch 为您提供相关数据和切实见解，以监控应用程序、响应系统范围的性能变化、优化资源利用率，并在统一视图中查看运营状况。CloudWatch 以日志、指标和事件的形式收集监控和运营数据，让您能够在统一查看在 AWS 和本地服务器上运行的资源、应用程序和服务。您可以使用 CloudWatch 检测环境中的异常行为、设置警报、并排显示日志和指标、执行自动化操作、排查问题，以及发现可确保应用程序正常运行的见解。

2. CloudWatch中的metrics是什么？包括哪些种类？我们可以如何使用metrics？

   > A metric represents a time-ordered set of data points that are published to CloudWatch. 
   包括哪些种类：CloudWatch、CloudWatch Events、CloudWatch Logs

3. CloudWatch Events是什么？可以应用在那些场景。

   > Amazon CloudWatch Events delivers a near real-time stream of system events that describe changes in Amazon Web Services (AWS) resources. 
   >
   > CloudWatch Events becomes aware of operational changes as they occur. CloudWatch Events responds to these operational changes and takes corrective action as necessary, by sending messages to respond to the environment, activating functions, making changes, and capturing state information.
   >
   > You can also use CloudWatch Events to schedule automated actions that self-trigger at certain times using cron or rate expressions.

4. 相关概念理解：metrics，periods，namespace，dimensions，statistics。

   - Metrics: A metric represents a time-ordered set of data points that are published to CloudWatch. 
   - Period: A *period* is the length of time associated with a specific Amazon CloudWatch statistic. 
   - Namespace: A *namespace* is a container for CloudWatch metrics. 
   - Dimension: A *dimension* is a name/value pair that is part of the identity of a metric.
   - Statistics: *Statistics* are metric data aggregations over specified periods of time. 

   