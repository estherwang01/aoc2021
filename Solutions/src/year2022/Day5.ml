let replace l pos a  = List.mapi (fun i x -> if i = pos then a else x) l
let len lst = List.fold_left (fun a b -> a + 1) 0 lst 

let strip str =
  if str = "" then "" else
  let search_pos init p next =
    let rec search i =
      if p i then raise(Failure "empty") else
      match str.[i] with
      | ' ' | '\n' | '\r' | '\t' -> search (next i)
      | _ -> i
    in
    search init
  in
  let len = String.length str in
  try
    let left = search_pos 0 (fun i -> i >= len) (succ)
    and right = search_pos (len - 1) (fun i -> i < 0) (pred)
    in
    String.sub str left (right - left + 1)
  with
  | Failure _ -> ""

let rec update_input input num from to1 = 
  if num == 0 then input else 
  let from_list = List.nth input from in 
  let to_list = List.nth input to1 in 
  let hd = List.hd from_list in 
  let new_from = List.tl from_list in 
  let new_to = hd :: to_list in 
  let input1 = replace input from new_from in 
  let input2 = replace input1 to1 new_to in 
  update_input input2 (num-1) from to1 

let rec get_n_head lst n acc = if n ==0 then acc else get_n_head (List.tl lst) (n-1) ((List.hd lst)::acc)  
let rec get_n_tail lst n = if n == 0 then lst else get_n_tail (List.tl lst) (n-1)

let update_input2 input num from to1 = 
  let from_list = List.nth input from in 
  let to_list = List.nth input to1 in 
  let nhd = get_n_head from_list num [] in 
  let ntail = get_n_tail from_list num in 
  let new_to = (List.rev nhd) @ to_list in 
  let input1 = replace input from ntail in 
  let input2 = replace input1 to1 new_to in 
  input2 

let process line input = 
  let line = strip line in 
  let stuff = String.split_on_char ' ' line in 
  let num = int_of_string (List.nth stuff 1) in 
  let from = int_of_string (List.nth stuff 3)-1 in 
  let to1 = int_of_string (List.nth stuff 5)-1 in 
  update_input2 input num from to1 

let read_file filename = 
  let chan = open_in filename in
  let input = ref (
              ('P'::'G'::'R'::'N'::[])::
              ('C'::'D'::'G'::'F'::'L'::'B'::'T'::'J'::[]):: 
              ('V'::'S'::'M'::[]):: 
              ('P'::'Z'::'C'::'R'::'S'::'L'::[]):: 
              ('Q'::'D'::'W'::'C'::'V'::'L'::'S'::'P' :: [])::
              ('S'::'M'::'D'::'W'::'N'::'T'::'C'::[])::
              ('P'::'W'::'G'::'D'::'H'::[]):: 
              ('V'::'M'::'C'::'S'::'H'::'P'::'L'::'Z'::[])::
              ('Z'::'G'::'W'::'L'::'F'::'P'::'R'::[])::[])
  in 
  try
    while true; do
      let line = input_line chan in 
      let input1 = process line !input in 
    input := input1
    done; !input
  with End_of_file ->
    close_in chan;
    !input

let post_process lines = 
  let top = List.map (fun a -> List.hd a) lines in 
  List.fold_left (fun a b -> a ^(String.make 1 b)) "" top 

let main = 
  let res = read_file "day5input.txt" in 
  let res1 = post_process res in 
  print_endline res1


