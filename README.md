
# Home Library (Alpha)
Home Library — the best way to handle your books storage. 

This software simplifies working with your home library. Organize your home library easily with filters, sorting, and search functions. The program will remember where your favorite book is, so you'll never lose it again!


| Section | Description                                                |
| :------- |:-----------------------------------------------------------|
| [Api Reference](#api-reference) | API's urls, methods, schemas and more                      |
| [Project Structure](#project-structure) | Project's structure and objects, methods, etc. descriptions|
| [Authors](#authors) | Project's authors                                          |

# API Reference

#### Login
```http
  POST /auth/login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. Your email |
| `password` | `string` | **Required** Your password

#### Refresh your access and refresh tokens (Uses Http-only "refresh-token" cookie)

```http
  POST /auth/refresh
```


# Project Structure
### /auth/ - directory contains auth-related classes/interfaces/exceptions etc.
### /auth/controllers - directory contains auth-controllers, handles auth-specific requests
 - **AuthController.java** - class that handles [auth-specific requests](#api-reference)


### /auth/controllers/schemas - directory contains auth controller-related schemas
 - **JwtTokenResponse.java** - response class for sending back to user access token.
 - **LoginCredentials.java** - request body class to receive email and password from [request body](#api-reference)


### /auth/exceptions - directory for auth-related exceptions
 - **AuthException.java** - parent (super) class for all auth-related exceptions

Other exceptions:
 - **FailedRegistrationException.java** 
 - **IncorrectPasswordException.java**
 - **InvalidRefreshToken.java**
 - **UserNotFoundException.java**

### /auth/repository - directory for auth-specific repositories
**LocalJwtProviderUsersDataRepository.java** - repository for managing database's **LocalJwtProviderUsersData** table. Table provides system with such information: user's 
- access token
- refresh token
- tokens expiration dates
- tokens valid states
Methods:

 - ```Optional<LocalJwtProviderUsersData> findByAccessToken(String accessToken);```

 - ```Optional<LocalJwtProviderUsersData> findByRefreshToken(String refreshToken);```

 - ```Optional<LocalJwtProviderUsersData> findByUserId(Long userId);```

 - ```Optional<LocalJwtProviderUsersData> findByUser(User user);```

**UserAuthProvidersTableRepository.class** - repository for getting User's auth providers, like GitHub, Google, Local Jwt. 
[Not implemented yet, only Local Jwt Provider is in use now]

 - ```Optional<UserAuthProvidersTable> findByUser(User user);```

**UserRepository.java** - repository provides system with User's information:
- id
- email
- email verification status
- encrypted password
- username
- registration date (createdAt column)
- role.


**AuthProvidersRepository.java** - repository provides system with existing auth providers, their ids and names


### /auth/repository/entities
 - **Role.java** - user's role database entity, contains roles ids, names.


### /auth/services/ - directory for service-related beans
**JwtAccessTokenService.java** - provides system with these methods:
 - ```String generateJwtAccessToken(String email)``` - ___!IMPORTANT - will be replaced with ```String generateJwtAccessToken(Long userId)```___

 - ```String getEmailFromJwtToken(String token)``` - ___!IMPORTANT - will be replaced with ```Long getUserIdFromJwtTokenn(String token)```___

**JwtRefreshTokenService.java** - provides system with refresh token generation utility.

 - ```String generateJwtRefreshToken()``` - generates secure 32 bytes length refresh token.

**LocalJwtProviderService.java** - composition of **JwtAccessTokenService** and **JwtRefreshTokenService** with some new methods.

 - ```void saveTokensToRepository(LoginCredentials loginCredentials, LocalJwtProviderTokensDto localJwtProviderTokensDto)```- saves token to LocalJwtProviderUsersDataRepository.

 - ```LocalJwtProviderTokensDto generateTokensWithEmail(String email)``` - uses **JwtAccessTokenService** and **JwtRefreshTokenService** to generate user's tokens. ___!IMPORTANT will be replaced with ```LocalJwtProviderTokensDto generateTokensWithEmail(Long userId)```___

 - ```LocalJwtProviderTokensDto generateTokens(String refreshToken)``` - uses ```LocalJwtProviderTokensDto generateTokensWithEmail(String email)``` to generate user's tokens by getting **LocalJwtProviderUsersData** with ```Optional<LocalJwtProviderUsersData> findByRefreshToken(String refreshToken)``` 


### /auth/dtos - data transfer objects
 - **LocalJwtProviderTokensDto.java** - data transfer object to transfer access and refresh tokens from various services and repositories.


### /security - security-related beans, configurations and utilities
 - **DefaultSecurityConfiguration.java** - setups defaultSecurityFilterChain, passwordEncoder, secureRandom bean, setSecureToSecurityCookies bean ___[!IMPORTANT will be renamed to securityConfig]___

 - **DevelopmentProfileSecurityConfiguration.java** - used to setup security configurations, when **development (dev) profile** is active

 - **SecurityConfig.java** - security configuration class.

## Authors

- [@ArtemGalkovsky](https://github.com/ArtemGalkovsky)
