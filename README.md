##CSRF Customization Example

An example of how to exclude particular url patterns and methods from csrf protection, if needed.

###Running the App

You can run the application from the command line using mvn jetty:run and login at http://localhost:8080/login

Username: user
Password: test

###Test Notes

Take a look at the ProtectedUrlSecurityTest and UnprotectedUrlSecurityTest for examples on how to test that the urls are locked down using Spring's Mock Mvc testing library.