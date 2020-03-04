package com.kazale.pontointeligente.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//Fara com que carregue o profile de teste - application-test.properties
@ActiveProfiles("test")
public class PontoInteligenteApplicationTests {

	@Test
	public void contextLoads() {
	}

}
