package br.com.desafiocielo.desafio3;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public abstract class SqsService_Desafio3 {

    AmazonSQS sqs = AmazonSQSClientBuilder.standard().build();

    public void sendUserToQueue(String url, Object user){
        sqs.sendMessage(new SendMessageRequest(url, user.toString()));
    }

    public ReceiveMessageResult receiveUserFromQueue(String url){
        return sqs.receiveMessage(new ReceiveMessageRequest(url));
    }

    public void deleteUser(String url, String receiptHandle) {
        sqs.deleteMessage(new DeleteMessageRequest(url, receiptHandle));
    }
}
