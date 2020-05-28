package message.model.service;

import common.ConnectionFactory;

public class MessageService {
	private ConnectionFactory factory;
	
	public MessageService() {
		factory=ConnectionFactory.getConnection();
	}
	
	public int notifyMessage(String userId, String nickname, String messageContent) {
		return 0;
		
	}
}
