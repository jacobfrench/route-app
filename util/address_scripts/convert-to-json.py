#!/usr/bin/python3

if __name__ == "__main__":
    file = open("out.txt", "r")
    lines = file.readlines()

    print('{"addresses":[')
    for line in lines:
        line = line.replace("\n", "")
        line = line.split(",")
        street = line[0]
        city = line[1].strip()
        state = line[2].strip()
        zip_code = line[3].strip()
        lat = line[4].strip()
        lng = line[5].strip()

        print('{"%s":"%s","%s":"%s","%s":"%s","%s":"%s","%s":"%s","%s":"%s"},' % ("street", street, "city", city, "state", state, "zip", zip_code, "lat", lat, "lng", lng))

        # {"accountId":32995,"fname":"Nellie","lname":"Timperley","email":"ntimperleyf@cargocollective.com","minit":"G","priPhone":"6098083344","altPhone":"5186413536"},


    print(']}')