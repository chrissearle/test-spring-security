This security works correctly in spring boot 2.x

* Anything under `/api/admin` requires admin role
* Anything under `/api/open` does not require login
* Anything under `/api` that is not `admin` or `open` just requires a user
* Anything under `/` that is not `api` does not require login

In spring boot 3 - /test is triggering login.

TODO - Find out why :)