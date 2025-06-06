package com.assessment.work.grandkapital.repository;

import com.assessment.work.grandkapital.model.entity.UserEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select distinct u from UserEntity u " +
            "left join fetch u.phones p " +
            "left join fetch u.emails e " +
//            "left join PhoneDataEntity p on u.id = p.user.id " +
//            "left join EmailDataEntity e on u.id = e.user.id " +
            "where (cast(:dateOfBirth as date) is null or u.dateOfBirth >= :dateOfBirth) " +
            "and (:phone is null or p.phone = :phone) " +
            "and (:email is null or e.email = :email) " +
            "and  (:name is null or lower(cast(u.name as text)) like lower(cast(concat('%', :name, '%') as text))) " +
            "order by u.name asc ")
    @EntityGraph(value = "account-emails-phones-graph")
    Page<UserEntity> findByParams(@Param("dateOfBirth") LocalDate dateOfBirth, @Param("phone") String phone, @Param("name") String name,
                                  @Param("email") String email, Pageable pageable);

    Optional<UserEntity> findByEmailsEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from UserEntity u where u.id = :id")
    Optional<UserEntity> findByIdPessimisticWrite(@Param("id") Long id);

    boolean existsById(Long id);
}