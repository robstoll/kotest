package com.sksamuel.kotest.property.arbitrary

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldHaveAtMostSize
import io.kotest.matchers.shouldBe
import io.kotest.property.arbitrary.Arb
import io.kotest.property.arbitrary.create
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.set
import io.kotest.property.checkAll
import io.kotest.property.exhaustive.Exhaustive
import io.kotest.property.exhaustive.constant
import io.kotest.property.forAll

class CollectionsTest : FunSpec({

   test("Arb.list should not include empty edgecases as first sample") {
      val numGen = Arb.list(Arb.create { it.random.nextInt() }, 1..100)
      forAll(1, numGen) { it.isNotEmpty() }
   }

   test("Arb.set should not include empty edgecases as first sample") {
      val numGen = Arb.set(Arb.create { it.random.nextInt() }, 1..100)
      forAll(1, numGen) { it.isNotEmpty() }
   }

   test("gen list should return lists of underlying generators") {
      val gen = Arb.list(Exhaustive.constant(1), 2..10)
      checkAll(gen) {
         it.shouldHaveAtLeastSize(2)
         it.shouldHaveAtMostSize(10)
         it.toSet() shouldBe setOf(1)
      }
   }
})