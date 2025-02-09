package com.unmsm.oevbackend.repository;

import com.unmsm.oevbackend.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("select e from Registration e where e.user.id = ?1")
    List<Registration> findRegistrationsByUserId(Long userId);

    @Query("select e from Registration e where e.conference.id = ?1")
    List<Registration> findRegistrationsByConferenceId(Long conferenceId);
}
