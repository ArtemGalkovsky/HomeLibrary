import "./HeaderItem.css"

export default function HeaderItem({ children, props }) {
    return <div className={"header-item-container " + (props.className ? props.className : "")}>
        {children}
    </div>
}