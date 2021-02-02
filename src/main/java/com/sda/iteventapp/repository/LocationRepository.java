package com.sda.iteventapp.repository;

import com.sda.iteventapp.model.City;
import com.sda.iteventapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<List<Location>> findLocationByCity(City city);

    Optional<Location> findLocationByNameAndAddress(String name, String address);
}
