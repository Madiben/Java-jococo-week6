# Video lecture

https://www.youtube.com/watch?v=0YwyzqQRY4w

# Gitlab Wiki page

https://docs.gitlab.com/ee/user/markdown.html

# Extend our code

```
    public int add(int aLeft, int aRight) {
        return aLeft + aRight;
    }
```

If we have a more complex code and plenty test cases, we want to know what parts of the original code tested weakly.

# Code coverage

The code coverage shows us which lines of the code run by our tests.
With this information we can find code that isn't used any tests, so if there is a bug in it, it does not reveals.


# Jacoco

For JAVA we can use Jacoco to measure code coverage, we have to extend our gradle file only.

First we have to import, so we change plugins to the following:
```
plugins {
    id 'application'
    id 'java'
    id 'jacoco'
}
```

When we activates Jacoco plugin it automatically creates a `jacocoTestReport` task in our gradle project.
We want to ask the gradle to run the code coverage report after every test, so we add
```
test {
    finalizedBy jacocoTestReport
}
```
that activates the `jacocoTestReport` right after the `test` task finished. 
Therefore if we run `gradle test` it will create the code coverage as well.

If we want to make sure the test results are exists we can say for the gradle if we want to run directly the `jacocoTestReport` by the `gradle jacocoTestReport` command, then please run the `test` before by
```
jacocoTestReport {
    dependsOn test
}
```

So our new shiny *build.gradle* file:
```
plugins {
    id 'application'
    id 'java'
    id 'jacoco'
}

repositories {
    mavenCentral()
}

sourceSets {
    test {
        java {
            srcDir 'test/java'
        }
    }
}

test {
    finalizedBy jacocoTestReport
}
jacocoTestReport {
    dependsOn test
}

dependencies {
	testCompile('junit:junit:4.12'){
		exclude group: 'org.hamcrest'
	}
	testCompile 'org.hamcrest:hamcrest-library:1.3'
}
```

By default it will create the report in `build/reports/jacoco/test/html` folder.

For more configuration: https://docs.gradle.org/current/userguide/jacoco_plugin.html

## If jacoco skipeed

Run `gradle clean` to clean the gradle build directory, that helps in many cases.

# Extend with function with branches

```
    public int max(int aLeft, int aRight) {
        if(aLeft > aRight){
            return aLeft;
        }else{
            return aRight;
        }
    }
```

We can check in the report what lines tested exactly.

# How can get this on the server?

We have to define to the GitLab we want to save some information from the specific stage by extending with artifacts part the job descriptor.

```
test_with_coverage:
    stage: test
    image: gradle
    script:
        - gradle test
    artifacts:
        paths:
            - build/reports/jacoco/test/html
        expire_in: 1 week
```

More info: https://docs.gitlab.com/ee/ci/pipelines/job_artifacts.html

From this point we can download from the job view directly our code coverage report or publish to somewhere in a next stage.

# Merge request closes issue

https://docs.gitlab.com/ee/user/project/issues/managing_issues.html#closing-issues-automatically

# Task

- Create a milestone titled "I learn how to use GitLab ([your name])".
- Create a wiki page. Set the title to your name. As content: 
    - Add a clickable link to google.
    - Add a code snippet into the page
    - Add link to your milestone
    - Add a link to one of your issue (created in the last session)
- Use the source files from this repository and commit to the sandbox project and write test for them.
- Achieve 100% testing coverage of the source code with the tests.
