# ESHOP
URL: https://eshop-arvin.koyeb.app/

## Tutorial 1
### Reflection 1
> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding standards that you have learned in this module. Write clean code principles and secure coding practices that have been applied to your code.  If you find any mistake in your source code, please explain how to improve your code. Please write your reflection inside the repository's README.md file.

I have used meaningful variables names, proper indentation, proper spacing, proper modularity when implementing edit and delete product features. I coded these two new features with MVC pattern. I also make sure I don't repeat the same code.

### Reflection 2
> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors? 

I think there should be at least one unit test for each method in a class. Code coverage is a metric that can help us understand how much of our source is tested. Thus, we can use code coverage metric to make sure of our unit tests. But, note that if we have 100% code coverage, that does not mean our code has no bugs or errors. It only means that all the lines of code are executed at least once.

> Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables. What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner! Please write your reflection inside the repository's README.md file.

I think the cleanliness of the code will be worse because some repetitions of the code we do. To avoid that, we could create a parent class that do common setup procedures and instance variables. Then, extend this parent class in the new Java class similar to the prior functional test suites. Another way, we could use the @BeforeEach and @AfterEach annotation to do common setup procedures and instance variables.

## Tutorial 2
### Reflection
Code quality issues that I fixed is inconsistent naming on the template file, for example, returning "homePage" on controller but the real file name is "HomePage". Not only that, I also removed unused parameter variables. I think these implementations have met the CI/CD principles. The implementation uses GitHub workflow to maintain each commit and pull requests to have the proper code (testing and SonarCube analysis). Not only that, we also make uses of it to deploy our project into Koyeb. 