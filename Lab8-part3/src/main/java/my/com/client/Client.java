package my.com.client;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import my.com.server.db.entity.Group;
import my.com.server.db.entity.Student;
import my.com.utility.SerializationUtility;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class Client implements AutoCloseable {
    private final Connection connection;
    private final Channel channel;
    private static final String requestQueueName = "rpcqname";

    public Client() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] argv) {
        try ( Client client = new Client()){

            System.out.println(client.findAllGroups());
            System.out.println(client.findAllStudents());
            Student student = Student.builder()
                    .firstName("name")
                    .lastName("last_name")
                    .groupId(2)
                    .build();
            client.createStudent(student);
            System.out.println(client.findAllStudents());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws IOException {
        connection.close();
    }
    private Object sendQuery(int operation, Serializable value)  {
        final String corrId = UUID.randomUUID().toString();
        try {
            String replyQueueName = channel.queueDeclare().getQueue();
            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(corrId)
                    .replyTo(replyQueueName)
                    .build();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            String query = operation + "#" + SerializationUtility.serialize(value);
            oos.writeObject(query);
            channel.basicPublish("", requestQueueName, props, bos.toByteArray());

            final CompletableFuture<String> futureResponse = new CompletableFuture<>();

            String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
                if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(delivery.getBody());
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    try {
                        futureResponse.complete((String) ois.readObject());

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }, consumerTag -> {
            });
            String response = futureResponse.get();
            channel.basicCancel(ctag);
            String[] fields = response.split("#");
                int comp_code = Integer.parseInt(fields[0]); // Код завершення
                Object result = SerializationUtility.deSerialize(fields[1]); // Результат операції
                if (comp_code == 0)
                    return result;
                else throw new IOException("Error while processing query");
        } catch (IOException | ExecutionException | InterruptedException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Group> findAllGroups() throws IOException {
        return (List<Group>) sendQuery(0, "");
    }
    public List<Student> findAllStudents() throws IOException {
        return (List<Student>) sendQuery(1, "");
    }
    public List<Student> getGroupStudents(Group group) throws IOException {
        return (List<Student>) sendQuery(2, group);
    }
    public Boolean createGroup(Group group) throws IOException {
        return (Boolean) sendQuery(3, group);
    }
    public Boolean createStudent(Student student) throws IOException {
        return (Boolean) sendQuery(4, student);
    }
    public Student getStudent(String name) throws IOException {
        return (Student) sendQuery(5, name);
    }
    public Group getGroup(String name) throws IOException {
        return (Group) sendQuery(6, name);
    }
    public Boolean deleteStudent(Student student) throws IOException {
        return (Boolean) sendQuery(7, student);
    }
    public Boolean deleteGroup(Group group) throws IOException {
        return (Boolean) sendQuery(8, group);
    }
    public Boolean updateStudent(Student student) throws IOException {
        return (Boolean) sendQuery(9, student);
    }
    public Boolean updateGroup(Group group) throws IOException {
        return (Boolean) sendQuery(10, group);
    }

}