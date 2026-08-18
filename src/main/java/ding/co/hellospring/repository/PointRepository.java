package ding.co.hellospring.repository;

import ding.co.hellospring.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
