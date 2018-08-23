from itertools import chain, islice

def chunks(iterable, size, format=iter):
    it = iter(iterable)
    while True:
        yield format(chain((it.next(),), islice(it, size - 1)))

data = ["a", "b", "c", "d", "e", "f", "g"]
for chunk in chunks(data, 3, tuple):
    print chunk
