package home_work.hw5.model.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "transaction", schema = "bank")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "source_bank_book_id", nullable = false)
    private BankBookEntity sourceBankBook;

    @OneToOne
    @JoinColumn(name = "target_bank_book_id", nullable = false)
    private BankBookEntity targetBankBook;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "initiation_date", nullable = false)
    private LocalDateTime initiationDate;

    @Column(name = "completion_date", nullable = false)
    private LocalDateTime completionDate;

    @OneToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TransactionEntity that = (TransactionEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
