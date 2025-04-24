package com.assessment.work.grandkapital.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "users")
@ToString(exclude = {"account", "emails", "phones"})
@EqualsAndHashCode(exclude = {"account", "emails", "phones"})
@NamedEntityGraph(name = "account-emails-phones-graph", attributeNodes = {
        @NamedAttributeNode(value = "account"),
        @NamedAttributeNode(value = "emails"),
        @NamedAttributeNode(value = "phones")
})
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_sq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, length = 500, columnDefinition = "text")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "password", nullable = false, length = 500)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AccountEntity account;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<EmailEntity> emails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PhoneEntity> phones;
}