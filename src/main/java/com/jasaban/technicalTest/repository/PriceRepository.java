package com.jasaban.technicalTest.repository;


import com.jasaban.technicalTest.model.Price;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, String> {

  @Query(
      "Select p from Price p where p.productId = :productId and p.brandId = :brandId and "
          + "PARSEDATETIME(:date,'yyy-MM-dd-HH.mm.ss') between p.startDate and p.endDate order by p.priority desc")
  List<Price> findByQueryFilter(
      @Param("brandId") int brandId, @Param("productId") int productId, @Param("date") String date);

  List<Price> findByProductIdAndBrandIdOrderByPriorityDesc(int productId, int brandId);
}
