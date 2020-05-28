package blacklist.model.service;

import common.ConnectionFactory;

public class BlacklistService {
	private ConnectionFactory factory;
	
	public BlacklistService() {
		factory=ConnectionFactory.getConnection();
	}
	
	public int insertBlacklist(String userId, String reason) {
		return 0;
		
	}
	
	public int deleteBlacklist(String userId) {
		return 0;
		
	}
}
