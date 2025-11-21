import "./Header.css"
import TextRouterLink from "../links/TextRouterLink.jsx";
import HeaderItem from "./items/HeaderItem.jsx";

export default function Header() {
    return <nav id="header-nav">
        <HeaderItem props={{className: "flex-end"}}>
            <TextRouterLink text="Login" toUrl="/login"/>
        </HeaderItem>
    </nav>
}