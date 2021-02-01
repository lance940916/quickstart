package com.snailwu.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.config.ConfigResource;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @author 吴庆龙
 * @date 2020/12/17 下午4:43
 */
public class KafkaClient {
    private static final AdminClient adminClient;

    static {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9090,localhost:9091,localhost:9092");
        props.put(AdminClientConfig.CLIENT_ID_CONFIG, "admin-client");
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "10000");
        adminClient = KafkaAdminClient.create(props);
    }

    public static void main(String[] args) throws Exception {
        // 创建 Topic
        createTopic("tom", 3, 1);
        createTopic("mike", 3, 2);
        createTopic("jerry", 3, 3);
//        createTopic("user", 1, 3);

//        describeConfig("order");

        // 列出 Topic
        listTopic();

        // Topic 详情
//        describeTopic("sms");

//        deleteTopic("user");

        // 修改消费者的 offset
//        alterConsumerGroupOffsets("wu", "order", 1, 1);

        // 创建分区
//        createPartitions("user", 3, Arrays.asList(Arrays.asList(3)));
//        createPartitions("user", 3, Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3)));

//        describeCluster();

//        listConsumerGroups();

//        Map<ConfigResource, Collection<AlterConfigOp>> configResourceCollectionMap = new HashMap<>();
//        new ConfigResource(ConfigResource.Type.BROKER, "");
//        configResourceCollectionMap.put()
//        adminClient.incrementalAlterConfigs(configResourceCollectionMap);

//        describeConfig("order");

        adminClient.close();
    }

    /**
     * 列出被修改的配置
     */
    private static void describeConfig(String topic) throws ExecutionException, InterruptedException {
        ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, topic);
        Map<ConfigResource, Config> configMap = adminClient.describeConfigs(Collections.singleton(configResource))
                .all().get();
        configMap.forEach((cr, c) -> {
            System.out.println(cr);
            for (ConfigEntry entry : c.entries()) {
                System.out.println(entry);
            }
            System.out.println("------------------");
        });
    }

    /**
     * 列出所有的 Topic
     */
    private static void listTopic() throws Exception {
        ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
        listTopicsOptions.listInternal(true);
        ListTopicsResult topicsResult = adminClient.listTopics(listTopicsOptions);
        System.out.println(topicsResult.names().get());
    }

    /**
     * 创建 Topic
     */
    private static void createTopic(String name, int numPartitions, int replicationFactor) throws Exception {
        NewTopic newTopic = new NewTopic(name, numPartitions, (short) replicationFactor);
        Map<String, String> configs = new HashMap<>();
        configs.put("cleanup.policy", "delete");
        newTopic.configs(configs);

        CreateTopicsResult createTopicsResult = adminClient.createTopics(Arrays.asList(newTopic));
        createTopicsResult.all().get();
    }

    /**
     * 删除 Topic
     */
    private static void deleteTopic(String... names) throws Exception {
        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList(names));
        deleteTopicsResult.all().get();
    }

    /**
     * 获取 Topic 详情
     */
    private static void describeTopic(String... names) throws Exception {
        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Arrays.asList(names));
        Map<String, TopicDescription> topicDescriptionMap = describeTopicsResult.all().get();
        for (Map.Entry<String, TopicDescription> entry : topicDescriptionMap.entrySet()) {
            TopicDescription description = entry.getValue();

            String name = description.name();
            boolean internal = description.isInternal();
            List<TopicPartitionInfo> partitions = description.partitions();
            System.out.println("Topic: " + name + ", internal: " + internal);
            for (TopicPartitionInfo partition : partitions) {
                System.out.println("----------------------------------------");
                System.out.println("PartitionId: " + partition.partition());
                System.out.println("Leader: " + partition.leader());
                System.out.println("Replicas:");
                List<Node> replicas = partition.replicas();
                for (Node replica : replicas) {
                    System.out.println("\t" + replica);
                }

                System.out.println("ISR:");
                List<Node> isr = partition.isr();
                for (Node replica : isr) {
                    System.out.println("\t" + replica);
                }
            }
        }
    }

    /**
     * 调整消费者的 offset
     */
    private static void alterConsumerGroupOffsets(String groupId, String topic, int partition, int offset) {
        Map<TopicPartition, OffsetAndMetadata> offsetMap = new HashMap<>();
        offsetMap.put(new TopicPartition(topic, partition), new OffsetAndMetadata(offset));
        adminClient.alterConsumerGroupOffsets(groupId, offsetMap);
    }

    /**
     * 创建 Partition
     * topic: 分区名
     * totalCount: 要调整为几个分区
     * 例1、目前有2个分区，每个分区有1个副本。执行：createPartitions("user", 3, Arrays.asList(Arrays.asList(3)));
     * 意思将分区增加到 3 个，指定新增的这个分区的副本在 broker id 是 3 的节点上
     * 例1、目前有1个分区，每个分区有3个副本。执行：createPartitions("user", 3, Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(2, 3, 1)));
     * 意思将分区增加到 3 个，指定新增的第一个分区的副本在 broker id 是 1, 2, 3 的节点上，第二个分区的副本在 broker id 是 2, 3, 1 的节点上
     * 这里指定了顺序，也可以顺序是一样的
     */
    private static void createPartitions(String topic, int totalCount, List<List<Integer>> newAssignments) {
        Map<String, NewPartitions> partitionsMap = new HashMap<>();
        partitionsMap.put(topic, NewPartitions.increaseTo(totalCount, newAssignments));
        adminClient.createPartitions(partitionsMap);
    }

    /**
     * 集群详情
     */
    private static void describeCluster() throws Exception {
        DescribeClusterResult describeClusterResult = adminClient.describeCluster();

        KafkaFuture<String> clusterId = describeClusterResult.clusterId();
        System.out.println("集群的ID: " + clusterId.get());

        KafkaFuture<Node> controller = describeClusterResult.controller();
        System.out.println("集群Leader: " + controller.get());

        Collection<Node> nodes = describeClusterResult.nodes().get();
        for (Node node : nodes) {
            System.out.println("集群节点: " + node);
        }
    }

    private static void listConsumerGroups() throws ExecutionException, InterruptedException {
        ListConsumerGroupsResult listConsumerGroupsResult = adminClient.listConsumerGroups();
        Collection<ConsumerGroupListing> consumerGroupListings = listConsumerGroupsResult.all().get();
        for (ConsumerGroupListing consumerGroupListing : consumerGroupListings) {
            System.out.println(consumerGroupListing);
        }
    }

}
