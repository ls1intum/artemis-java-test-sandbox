package de.tum.in.testsecurity;

import static de.tum.in.testutil.CustomConditions.*;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.testkit.engine.EventConditions.*;

import org.junit.ComparisonFailure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.platform.testkit.engine.EngineTestKit;
import org.junit.platform.testkit.engine.Events;
import org.opentest4j.AssertionFailedError;

import de.tum.in.testutil.TestTest;

public class SecurityTest {

	private final String doSystemExit = "doSystemExit";
	private final String exceedTimeLimit = "exceedTimeLimit";
	private final String longOutputJUnit4 = "longOutputJUnit4";
	private final String longOutputJUnit5 = "longOutputJUnit5";
	private final String makeUTF8Error = "makeUTF8Error";
	private final String testPenguin1 = "testPenguin1";
	private final String testPenguin2 = "testPenguin2";
	private final String testPolarBear = "testPolarBear";
	private final String testSquareCorrect = "testSquareCorrect";
	private final String testSquareWrong = "testSquareWrong";
	private final String testTooManyreads = "testTooManyreads";
	private final String useReflectionNormal = "useReflectionNormal";
	private final String useReflectionTrick = "useReflectionTrick";
	private final String weUseReflection = "weUseReflection";
	private final String accessPathNormal = "accessPathNormal";
	private final String accessPathAllowed = "accessPathAllowed";
	private final String accessPathTest = "accessPathTest";
	private final String weAccessPath = "weAccessPath";
	private final String testMaliciousExceptionA = "testMaliciousExceptionA";
	private final String testMaliciousExceptionB = "testMaliciousExceptionB";
	private final String testExecuteGit = "testExecuteGit";
	private final String testThreadGroup = "testThreadGroup";
	private final String testEvilPermission = "testEvilPermission";
	/**
	 * Currently unused because this is very inconsistent depending on the CI
	 * environment
	 */
	@SuppressWarnings("unused")
	private final String testThreadBomb = "testThreadBomb";
	private final String threadWhitelistingWithPathFail = "threadWhitelistingWithPathFail";
	private final String threadWhitelistingWithPathCorrect = "threadWhitelistingWithPathCorrect";
	private final String threadWhitelistingWithPathPenguin = "threadWhitelistingWithPathPenguin";
	private final String packageBlacklistingPenguinUtil = "packageBlacklistingPenguinUtil";
	private final String packageBlacklistingPenguinJava = "packageBlacklistingPenguinJava";
	private final String packageBlacklistingPenguinAll = "packageBlacklistingPenguinAll";
	private final String packageBlackAndWhitelistingPenguin = "packageBlackAndWhitelistingPenguin";

	private static Events tests;

	@BeforeAll
	@Tag("test-test")
	static void verifySecurity() {
		var results = EngineTestKit.engine("junit-jupiter").selectors(selectClass(SecurityUser.class)).execute();
		tests = results.testEvents();

		results.containerEvents().assertStatistics(stats -> stats.started(2).succeeded(2));
		tests.assertStatistics(stats -> stats.started(30));
	}

	@TestTest
	void test_testPenguin1() {
		tests.assertThatEvents().haveExactly(1, event(test(testPenguin1), finishedSuccessfullyRep()));
	}

	@TestTest
	void test_testPolarBear() {
		tests.assertThatEvents().haveExactly(1, event(test(testPolarBear), finishedSuccessfullyRep()));
	}

	@TestTest
	void test_testSquareCorrect() {
		tests.assertThatEvents().haveExactly(1, event(test(testSquareCorrect), finishedSuccessfullyRep()));
	}

	@TestTest
	void test_weUseReflection() {
		tests.assertThatEvents().haveExactly(1, event(test(weUseReflection), finishedSuccessfullyRep()));
	}

	@TestTest
	void test_weAccessPath() {
		tests.assertThatEvents().haveExactly(1, event(test(weAccessPath), finishedSuccessfullyRep()));
	}

	@TestTest
	void test_accessPathAllowed() {
		tests.assertThatEvents().haveExactly(1, event(test(accessPathAllowed), finishedSuccessfullyRep()));
	}

	@TestTest
	void test_testEvilPermission() {
		tests.assertThatEvents().haveExactly(1, event(test(testEvilPermission), finishedSuccessfullyRep()));
	}

	@TestTest
	void test_threadWhitelistingWithPathCorrect() {
		tests.assertThatEvents().haveExactly(1,
				event(test(threadWhitelistingWithPathCorrect), finishedSuccessfullyRep()));
	}

	@TestTest
	void test_packageBlackAndWhitelistingPenguin() {
		tests.assertThatEvents().haveExactly(1,
				event(test(packageBlackAndWhitelistingPenguin), finishedSuccessfullyRep()));
	}

	@TestTest
	void test_doSystemExit() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(doSystemExit, SecurityException.class));
	}

	@TestTest
	void test_useReflectionNormal() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(useReflectionNormal, SecurityException.class));
	}

	@TestTest
	void test_useReflectionTrick() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(useReflectionTrick, SecurityException.class));
	}

	@TestTest
	void test_accessPathNormal() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(accessPathNormal, SecurityException.class));
	}

	@TestTest
	void test_accessPathTest() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(accessPathTest, SecurityException.class));
	}

	@TestTest
	void test_testMaliciousExceptionA() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(testMaliciousExceptionA, SecurityException.class));
	}

	@TestTest
	void test_testExecuteGit() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(testExecuteGit, SecurityException.class));
	}

	@TestTest
	void test_testThreadGroup() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(testThreadGroup, SecurityException.class));
	}

	@TestTest
	void test_testMaliciousExceptionB() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(testMaliciousExceptionB, SecurityException.class));
	}

	@TestTest
	void test_testThreadBomb() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(testThreadBomb, SecurityException.class));
	}

	@TestTest
	void test_threadWhitelistingWithPathFail() {
		tests.assertThatEvents().haveExactly(1,
				testFailedWith(threadWhitelistingWithPathFail, SecurityException.class));
	}

	@TestTest
	void test_threadWhitelistingWithPathPenguin() {
		tests.assertThatEvents().haveExactly(1,
				testFailedWith(threadWhitelistingWithPathPenguin, SecurityException.class));
	}

	@TestTest
	void test_packageBlacklistingPenguinUtil() {
		tests.assertThatEvents().haveExactly(1,
				testFailedWith(packageBlacklistingPenguinUtil, SecurityException.class));
	}

	@TestTest
	void test_packageBlacklistingPenguinJava() {
		tests.assertThatEvents().haveExactly(1,
				testFailedWith(packageBlacklistingPenguinJava, SecurityException.class));
	}

	@TestTest
	void test_packageBlacklistingPenguinAll() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(packageBlacklistingPenguinAll, SecurityException.class));
	}

	@TestTest
	void test_exceedTimeLimit() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(exceedTimeLimit, AssertionFailedError.class));
	}

	@TestTest
	void test_longOutputJUnit4() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(longOutputJUnit4, ComparisonFailure.class));
	}

	@TestTest
	void test_longOutputJUnit5() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(longOutputJUnit5, AssertionFailedError.class));
	}

	@TestTest
	void test_makeUTF8Error() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(makeUTF8Error, IllegalArgumentException.class));
	}

	@TestTest
	void test_testPenguin2() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(testPenguin2, ComparisonFailure.class));
	}

	@TestTest
	void test_testSquareWrong() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(testSquareWrong, IllegalStateException.class));
	}

	@TestTest
	void test_testTooManyreads() {
		tests.assertThatEvents().haveExactly(1, testFailedWith(testTooManyreads, IllegalStateException.class));
	}
}
