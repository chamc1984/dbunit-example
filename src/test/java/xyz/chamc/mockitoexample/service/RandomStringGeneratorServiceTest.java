package xyz.chamc.mockitoexample.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
public class RandomStringGeneratorServiceTest {

  // テスト対象
  @Autowired
  private RandomStringGeneratorService randomStringGeneratorService;

  // モック対象
  @MockBean
  private TxnRandomStringRepository txnRandomStringrepository;

  /**
   * テスト前処理.
   * 
   */
  @BeforeEach
  public void beforeEach() {
    // DBに書き込まない.
    when(txnRandomStringrepository.save(any())).thenReturn(new TxnRandomStringEntity());
  }

  /**
   * 正常系テスト.
   * 
   */
  @Test
  public void normalTest() {
    String result = randomStringGeneratorService.execute(10);

    assertEquals(10, result.length());
  }

  /**
   * privateメソッドのテストコード.
   * 
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   */
  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
  public void privateTest(int charsize) throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {

    // リフレクション
    Method method =
        RandomStringGeneratorService.class.getDeclaredMethod("generateRandomString", int.class);
    method.setAccessible(true);

    String result = (String) method.invoke(randomStringGeneratorService, charsize);

    // AssertJでアサーション
    assertThat(result).isNotEmpty();
    assertThat(result).hasSize(charsize);
    assertThat(result).containsPattern("^[A-Z0-9]+?$");

  }

}
