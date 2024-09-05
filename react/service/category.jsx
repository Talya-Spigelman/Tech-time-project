import axios from "axios";
// import { Category } from "../models/category";

export const API = axios.create({
    baseURL: "http://localhost:8585/api/category"
});

//create new category
export const createCategory=(newCategory)=>API.post("",newCategory);

//get all categoris
export const fetchCategory=()=>API.get("/getCategories");

//get a specific category
export const fetchSingleCategory = async (id) => await API.get(`/${id}`)

//delete category
export const deleteCategory=(id)=>API.delete(`/${id}`);

// update category
export const updateCategory=(category)=>API.put(`/${category.id}`,category);