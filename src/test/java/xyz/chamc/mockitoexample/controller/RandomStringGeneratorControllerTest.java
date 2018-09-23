package xyz.chamc.mockitoexample.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import xyz.chamc.mockitoexample.domain.model.TxnRandomStringEntity;
import xyz.chamc.mockitoexample.domain.repository.TxnRandomStringRepository;

@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest()
public class RandomStringGeneratorControllerTest {

  // モックにする対象クラス
  @MockBean
  private TxnRandomStringRepository txnRandomStringRepository;

  // モックを差し込む対象クラス
  @Autowired
  private RandomStringGeneratorController randomStringGeneratorController;

  @BeforeEach
  public void beforeEach() {
    when(txnRandomStringRepository.save(any())).thenReturn(new TxnRandomStringEntity());
  }

  @Test
  public void successCase() throws Exception {
    String result = randomStringGeneratorController.randomStringGenerate(10);

    assertEquals(10, result.length());
    verify(txnRandomStringRepository, times(1)).save(any());

  }

}
