https://my.oschina.net/u/2948566/blog/1626278

说明：

deadLetterExchange()声明了一个Direct 类型的Exchange （死信队列跟交换机没有关系）

deadLetterQueue() 声明了一个队列   这个队列 跟前面我们声明的队列不一样    注入了 Map<String,Object> 参数    下面的概念非常重要

x-dead-letter-exchange 来标识一个交换机  x-dead-letter-routing-key  来标识一个绑定键（RoutingKey） 
 这个绑定键 是分配给 标识的交换机的   如果没有特殊指定 声明队列的原routingkey , 如果有队列通过此绑定键 绑定到交换机    那么死信会被该交换机转发到 该队列上  通过监听 可对消息进行消费

可以打个比方  这个是为主力队员 设置了一个替补   如果主力 “死”了   他的活 替补接手  这样更好理解

deadLetterBinding() 对这个带参队列 进行了 和交换机的规则绑定   等下 消费者 先把消息通过交换机投递到该队列中去   然后制造条件发生“死信”

启动redis:  D:\Redis


MQ user/1234

http://localhost:15672/#/vhosts

/*******springBoot多模块项目框架*************/

F:\spring-boot-base-master  

/*******springBoot多模块项目框架*************/

https://github.com/chuqianfeng/byron.git


websocket 原代码 E:\gs-messaging-stomp-websocket-master