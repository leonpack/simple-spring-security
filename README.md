Spring Security Flow:
Authentication Flow
When an incoming request reaches our system, Spring Security starts by choosing the right security filter to process that request 
(Is the request a POST containing username and password elements? => UsernamePasswordAuthenticationFilter is chosen. 
Is the request having a header “Authorization : Basic base64encoded(username:password)”? => BasicAuthenticationFilter is chosen… and so the chaining goes on). 
When a filter had successfully retrieved Authentication informations from the request, the AuthenticationManager is invoked to authenticate the request. via its implementation, 
the AuthenticationManager goes through each of the provided AuthenticationProvider(s) and try to authenticate the user based on the passed Authentication Object. 
when the Authentication is successful, and a matching user is found, an Authentication Object containing the user Authorities (which will be used to manage the user access to the system’s resources) is returned and set into the SecurityContext.
source: https://medium.com/@haytambenayed/how-does-spring-security-work-internally-525d359d7af
