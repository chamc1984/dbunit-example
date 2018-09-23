package xyz.chamc.mockitoexample.domain.model;

import java.time.LocalDateTime;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import lombok.Data;

@Entity
@Table(name = "TxnRandomString")
@Data
public class TxnRandomStringEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int serialNo;

  private String randomString;

  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime createDate;

  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime updateDate;
}
