import { useLocation, useNavigate, useRouteError } from "react-router-dom";

export default function AllowedAccess() {

  const navigate = useNavigate()

  const onHandleSubmit = () => {
    navigate('/')
  }

  const location = useLocation();


  return (
    <div className="flex flex-col items-center py-[30vh]">
      <p className="text-base text-[#5473E3]">Welcome! User:  <b>{location.state.username}</b>, Allowed Access</p>
      <button
            type="submit"
            className={`rounded-full bg-[#3D5FD9] text-[#F5F7FF] w-[25rem] p-3 mt-5 hover:bg-[#2347C5] mb-5`}
            onClick={onHandleSubmit}
            >
            LOG OUT
      </button>
    </div>
  );
}