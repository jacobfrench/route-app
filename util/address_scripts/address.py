#!/usr/bin/python

def main():
    file = open('raw_address.txt', 'r')
    lines = file.readlines()

    addresses = set()
    for line in lines:
        if(line.startswith("Phone") or line.startswith("NameA") or line.startswith("NameB") or line.startswith("NameC")):
            continue
        if(len(line.strip()) != 0 and line.startswith("Address")):
            line = line.replace('Address', "")
            line = line.replace("Save", "")
            line = line.replace("Addres", "")
            line = line.strip("\n")
            addresses.add(line)
        else:
            line = line.strip("\n")
            addresses.add(line)

    locations = []
    out = open('out.txt', 'w')
    for address in addresses:
      locations.append(address.split(','))
      out.write(address)
      out.write("\n")
    out.close()

    for l in locations:
      print(l)



if __name__ == "__main__":
    main()