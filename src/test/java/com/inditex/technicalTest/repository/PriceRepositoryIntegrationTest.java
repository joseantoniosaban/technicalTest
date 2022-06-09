package com.inditex.technicalTest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

// @Sql({"/data.sql"})
@SpringBootTest
@Transactional
public class PriceRepositoryIntegrationTest {

  @Autowired private PriceRepository priceRepository;

  @Test
  @Rollback
  public void testFindAll() {
    assertEquals(4, priceRepository.findAll().size());
  }

  @Test
  @Rollback
  public void testDataNotFound() {
    assertEquals(0, priceRepository.findByQueryFilter(1, 35456, "2021-12-31-23.59.59").size());
  }

  @Test
  @Rollback
  public void test1_FindPriceByQueryFilter() {
    assertEquals(1, priceRepository.findByQueryFilter(1, 35455, "2020-06-14-10.00.00").size());
  }

  @Test
  @Rollback
  public void test2_FindPriceByQueryFilter() {
    assertEquals(2, priceRepository.findByQueryFilter(1, 35455, "2020-06-14-16.00.00").size());
  }

  @Test
  @Rollback
  public void test3_FindPriceByQueryFilter() {
    assertEquals(1, priceRepository.findByQueryFilter(1, 35455, "2020-06-14-21.00.00").size());
  }

  @Test
  @Rollback
  public void test4_FindPriceByQueryFilter() {
    assertEquals(2, priceRepository.findByQueryFilter(1, 35455, "2020-06-15-10.00.00").size());
  }

  @Test
  @Rollback
  public void test5_FindPriceByQueryFilter() {
    assertEquals(2, priceRepository.findByQueryFilter(1, 35455, "2020-06-16-21.00.00").size());
  }

  @Test
  @Rollback
  public void testDataNotFoundByProductIdAndBrandId() {
    assertEquals(0, priceRepository.findByProductIdAndBrandIdOrderByPriorityDesc(35456, 1).size());
  }

  @Test
  @Rollback
  public void test1_FindPriceByProductIdAndBrandId() {
    assertEquals(4, priceRepository.findByProductIdAndBrandIdOrderByPriorityDesc(35455, 1).size());
  }

  @Test
  @Rollback
  public void test2_FindPriceByProductIdAndBrandId() {
    assertEquals(
        1,
        priceRepository
            .findByProductIdAndBrandIdOrderByPriorityDesc(35455, 1)
            .get(0)
            .getPriority());
  }
}
