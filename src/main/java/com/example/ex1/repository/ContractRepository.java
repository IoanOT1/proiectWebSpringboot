package com.example.ex1.repository;

import com.example.ex1.model.Contract;
import com.example.ex1.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Integer> {
    // TODO limit
    @Query( "select sp, count(*) from Contract c " +
            "inner join SubscriptionPlan sp " +
            "on sp.subscriptionPlanId = c.subscriptionPlan.subscriptionPlanId " +
            "group by sp ")
    List<SubscriptionPlan> getNMostCommonSubscriptionPlans();
}
