import { Link, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import axios from "axios";
import { useEffect, useState } from "react";
const { VITE_API_BASE_URL } = import.meta.env;

type ICreateUserData = {
    username: string;
    password: string;
}

const schema = yup.object({
    username: yup.string().required("username field is required."),
    password: yup.string().required("Password field is required.")
})

export default function SignIn() {

    const navigate = useNavigate()

    const { register, handleSubmit: onSubmit,
        formState: { errors }
    } = useForm<ICreateUserData>({ resolver: yupResolver(schema) });

    const handleSubmit = (data: ICreateUserData) => {

        const config = {
            headers: {
                'Access-Control-Allow-Origin': '*'
            }
          };

        axios.post(`https://young-waterfall-5981.fly.dev/user-credentials/valid`, data, config)
            .then(response => {
                if(response.data){
                    navigate('/userProfile', { state: data })
                } else {
                    alert("Access denied!")      
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });      
    }

    const onHandleSubmit = () => {
        console.log("Click")
     }

    return (
        <div className="flex flex-col items-center">

            <form onSubmit={onSubmit(handleSubmit)} className="flex h-[calc(100vh-95px)] flex-col justify-center items-center outline-none">

                <p className="place-self-start font-semibold text-base text-[#5473E3]">Login to the system</p>

                <input
                    {...register("username")}
                    type="username"
                    placeholder="Username"
                    className={errors.username ? "block peer rounded-[5px] w-[25rem]  mt-5 border-[#C93B32] focus:outline-none focus:border-[#C93B32]  focus:ring-1 focus:ring-[#C93B32]" : "block peer rounded-[5px] border-[#AEBBCD] w-[25rem] mt-5 focus:outline-none focus:ring-1"}
                />
                <span className="place-self-start text-[14px] text-[#C93B32]">
                    {errors.username?.message}
                </span>

                <input
                    {...register("password")}
                    type="password"
                    placeholder="Password"
                    className={errors.password ? "block peer rounded-[5px] w-[25rem] mt-5 border-[#C93B32] focus:outline-none focus:border-[#C93B32]  focus:ring-1 focus:ring-[#C93B32]" : "block peer rounded-[5px] border-[#AEBBCD] w-[25rem] mt-5 focus:outline-none focus:ring-1"}
                />
                <span className="place-self-start text-[14px] text-[#C93B32]">
                    {errors.password?.message}
                </span>

                <button
                    type="submit"
                    className={`rounded-full bg-[#3D5FD9] text-[#F5F7FF] w-[25rem] p-3 mt-5 hover:bg-[#2347C5] mb-5`}
                    onClick={onHandleSubmit}
                >
                    SIGN IN
                </button>
            </form>
        </div>

    )
}
