# sky-library-store

1. The solution uses `gradle` as its build tool. To run test in command line:
   `./gradlew clean test`
2. The Implementation of `BookService` is `BookServiceImpl`. Its test is `BookServiceImplTest`
   I don't normally use Impl as a suffix to a class name as a name like XXXXImpl does not reveal much to readers.
   In a real system, I normally try to use a name giving out some useful information about the implementation. But 
   in this case, lacking of context, I have reluctantly called it `BookServiceImpl` 
3. The implementation is not trying to be comprehensive to cover all cases. However, it passes all the criteria set out 
   in the requirement. The `BookServiceImplTest` captures all the test scenarios.
