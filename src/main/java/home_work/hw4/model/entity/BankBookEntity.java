package home_work.hw4.model.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "bank_book", schema = "ad")
@Validated
public class BankBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    @NotNull(message = "Не должен быть пустым!")
    private Integer userId;

    @Column(name = "number")
    @NotNull(message = "Не должен быть пустым!")
    private String number;

    @Column(name = "amount")
    @NotNull(message = "Не должен быть пустым!")
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    @NotNull(message = "Не должен быть пустым!")
    private String currency;



}
