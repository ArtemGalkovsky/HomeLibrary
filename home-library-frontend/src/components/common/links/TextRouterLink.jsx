import {Link} from "react-router";
import "./TextRouterLink.css"

export default function TextRouterLink({ text, toUrl }) {
    return <div className="text-router-link_link-container">
        <Link className="text-router-link" to={toUrl}>{text}</Link>
    </div>
}