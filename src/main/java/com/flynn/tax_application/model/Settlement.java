package com.flynn.tax_application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "setlements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "settlement_type", nullable = false)
    private SettlementType settlementType;

    @Column(name = "is_done", nullable = false)
    private boolean isDone = false;

    @Column(name = "year_month_stamp", nullable = false)
    private String yearMonthStamp;

    @Column(name = "done_at")
    private LocalDateTime doneAt;

    @Column(name = "until_when")
    private LocalDateTime untilWhen;

    @Column(name = "auto_extend", nullable = false)
    private boolean autoExtend = false;

    @ManyToOne
    @JoinColumn(name = "tax_client_id")
    private TaxClient taxClient;

    @Column(name = "notes")
    private String notes;
}
