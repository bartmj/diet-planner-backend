import Input from './Input';
import ButtonWithProgress from './ButtonWithProgress';

const UserSignupPage = () => {

    return (
        <div className="container">
            <h1 className="text-center">Sign Up</h1>
            <div className="col-12 mb-3">
                <Input
                    name="displayName"
                    label="Display Name"
                    placeholder="Your display name"
                    // value={form.displayName}
                    // onChange={onChange}
                    // hasError={errors.displayName && true}
                    // error={errors.displayName}
                />
            </div>
            <div className="col-12 mb-3">
                <Input
                    name="username"
                    label="Username"
                    placeholder="Your username"
                    // value={form.username}
                    // onChange={onChange}
                    // hasError={errors.username && true}
                    // error={errors.username}
                />
            </div>
            <div className="col-12 mb-3">
                <Input
                    name="password"
                    label="Password"
                    placeholder="Your password"
                    type="password"
                    // value={form.password}
                    // onChange={onChange}
                    // hasError={errors.password && true}
                    // error={errors.password}
                />
            </div>
            <div className="col-12 mb-3">
                <Input
                    name="passwordRepeat"
                    label="Password Repeat"
                    placeholder="Repeat your password"
                    type="password"
                    // value={form.passwordRepeat}
                    // onChange={onChange}
                    // hasError={passwordRepeatError && true}
                    // error={passwordRepeatError}
                />
            </div>
            <div className="text-center">
                <ButtonWithProgress
                    // onClick={onClickSignup}
                    // disabled={pendingApiCall || passwordRepeatError ? true : false}
                    // pendingApiCall={pendingApiCall}
                    text="Sign Up"
                />
            </div>
        </div>
    );
}

export default UserSignupPage;