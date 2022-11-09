const Input = (props) => {

    return (
        <div>
            {props.label && <label>{props.label}</label>}
            <input
                name={props.name}
                // type={props.type || 'text'}
                placeholder={props.placeholder}
                value={props.value}
                // onChange={props.onChange}
            />
            {/*{props.hasError && (*/}
            {/*    <span className="invalid-feedback">{props.error}</span>*/}
            {/*)}*/}
        </div>
    )
}

export default Input;

