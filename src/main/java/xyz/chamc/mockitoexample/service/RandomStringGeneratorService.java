package xyz.chamc.mockitoexample.service;

import java.time.LocalDateTime;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chamc.mockitoexample.domain.model.TxnRandomStringEntity;
import xyz.chamc.mockitoexample.domain.repository.TxnRandomStringRepository;

@Service
public class RandomStringGeneratorService {

	static final String USABLECHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@Autowired
	TxnRandomStringRepository txnRandomStringRepository;

	public String execute(int wordcount) {
		String res = this.generateRandomString(wordcount);
		this.insertRandomString(res);
		return res;
	}
	
	/**
	 * ランダム文字列を生成する.
	 * 
	 * @param wordcount 生成するランダム文字列の文字数
	 * @return 生成したランダム文字列
	 */
  private String generateRandomString(int wordcount) {
		
		String res = RandomStringUtils.random(wordcount, USABLECHAR);
		return res;	
	}

	/**
	 * 生成したランダム文字列をDBにインサートする.
	 * 
	 * @param str DBにインサートする文字列
	 */
	private void insertRandomString(String str) {
		LocalDateTime ldt = LocalDateTime.now();
		
		TxnRandomStringEntity entity = new TxnRandomStringEntity();
		entity.setRandomString(str);
		entity.setCreateDate(ldt);
		entity.setUpdateDate(ldt);

		txnRandomStringRepository.save(entity);
	}
}
