import LoginForm from "./LoginForm.jsx";
import "./LoginForm.css"
import {useState} from "react";

export default function LoginPage() {
    const [loginState, setLoginState] = useState("none");

    function storeJwtTokenInLocalStorage(jwtToken) {
        localStorage.setItem("auth", jwtToken)
    }

    async function getJwtToken(email, password) {
        const response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: {
                email,
                password,
            }
        })

        if (!response.ok) {
            setLoginState(() => "failed")
            return
        }

        storeJwtTokenInLocalStorage(response.body)
        setLoginState("completed")
    }

    function onLoginSubmit(formData) {
        const email = formData.get("email")
        const password = formData.get("password")

        getJwtToken(email, password)
    }

    return <>
        <h1>{loginState}</h1>
        <LoginForm>
            <form action={onLoginSubmit} id="login-form">
                <input type="email" name="email" id="email" placeholder="Your email"/>
                <input type="password" name="password" id="password" placeholder="Your password"/>

                <button type="submit">Log in</button>
            </form>
        </LoginForm>
    </>
}