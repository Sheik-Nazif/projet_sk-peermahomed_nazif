package com.example.projet_skpeermahomed_nazif.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"worker", "courantAccount", "epargnAccount"})
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "postal", length = 10)
    private String postal;

    @Column(name = "city")
    private String city;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @ManyToOne(optional = true)
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @OneToOne
    @JoinColumn(name = "courant_account_id", unique = true)
    private Account courantAccount;

    @OneToOne
    @JoinColumn(name = "epargn_account_id", unique = true)
    private Account epargnAccount;
}
